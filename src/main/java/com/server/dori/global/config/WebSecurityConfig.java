package com.server.dori.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.dori.domain.auth.exception.AuthNotFoundException;
import com.server.dori.domain.auth.exception.AuthUnauthorizedException;
import com.server.dori.domain.auth.presentation.dto.response.TokenDto;
import com.server.dori.domain.auth.service.QueryAuthService;
import com.server.dori.global.jwt.JwtFilter;
import com.server.dori.global.oauth2.CustomOAuth2UserService;
import com.server.dori.global.response.ApiResponse;
import com.server.dori.global.response.exception.ApiErrorResponse;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final JwtFilter jwtFilter;
	private final ObjectMapper objectMapper;
	private final QueryAuthService queryAuthService;
	private final CustomOAuth2UserService customOAuth2UserService;

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring()
			.requestMatchers("/h2-console/**");
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth
				// 스웨거
				.requestMatchers(
					"/swagger-ui/**",
					"/v3/api-docs/**",
					"/swagger-resources/**",
					"/webjars/**"
				).permitAll()
				// 인증
				.requestMatchers(
					"/oauth2/authorization/**",
					"/api/v1/auth/oauth2/kakao",
					"/api/v1/auth/signup",
					"/api/v1/auth/reissue"
				).permitAll()
				.anyRequest()
				.authenticated())

			.oauth2Login(oauth2 -> oauth2
				// 인증 요청
				.authorizationEndpoint(endpoint -> endpoint
					.baseUri("/oauth2/authorization"))

				// 리다이렉션
				.redirectionEndpoint(endpoint -> endpoint
					.baseUri("/api/v1/auth/oauth2/*"))

				// 유저 정보
				.userInfoEndpoint(endpoint -> endpoint
					.userService(customOAuth2UserService))

				// 인증 성공
				.successHandler((request, response, authentication) -> {
					OAuth2User oauth2User = (OAuth2User)authentication.getPrincipal();
					TokenDto tokenDto = queryAuthService.oauth2Login(oauth2User);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().write(objectMapper.writeValueAsString(ApiResponse.ok(tokenDto)));
				})

				// 인증 실패
				.failureHandler((request, response, exception) -> {
					response.setContentType("application/json;charset=UTF-8");
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					AuthNotFoundException authException = AuthNotFoundException.oauthUserNotFound();
					ApiErrorResponse errorResponse = new ApiErrorResponse(
						false,
						authException.getCode(),
						authException.getMessage(),
						null
					);
					response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
				}))

			// JWT
			.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.httpBasic(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.csrf(AbstractHttpConfigurer::disable)

			.exceptionHandling(exception -> exception
				.authenticationEntryPoint(jwtAuthenticationEntryPoint())
				.accessDeniedHandler(jwtAccessDeniedHandler()))

			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(
		AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public AuthenticationEntryPoint jwtAuthenticationEntryPoint() {
		return ((request, response, authException) -> {
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			AuthUnauthorizedException authException2 = AuthUnauthorizedException.invalidToken();
			ApiErrorResponse errorResponse = new ApiErrorResponse(
				false,
				authException2.getCode(),
				authException2.getMessage(),
				null
			);
			response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
		});
	}

	@Bean
	public AccessDeniedHandler jwtAccessDeniedHandler() {
		return (request, response, accessDeniedException) -> {
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			AuthUnauthorizedException authException = AuthUnauthorizedException.invalidToken();
			ApiErrorResponse errorResponse = new ApiErrorResponse(
				false,
				authException.getCode(),
				authException.getMessage(),
				null
			);
			response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
		};
	}
}
