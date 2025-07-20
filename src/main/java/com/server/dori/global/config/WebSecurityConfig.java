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
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.dori.domain.auth.exception.AuthUnauthorizedException;
import com.server.dori.global.jwt.JwtFilter;
import com.server.dori.global.response.exception.ApiErrorResponse;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final JwtFilter jwtFilter;
	private final ObjectMapper objectMapper;

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
					"/api/v1/auth/oauth2/kakao",
					"/api/v1/auth/reissue"
				).permitAll()
				.anyRequest()
				.authenticated())

			// JWT 설정
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
