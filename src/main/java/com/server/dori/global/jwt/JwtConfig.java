package com.server.dori.global.jwt;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {

	private final JwtTokenProvider jwtTokenProvider;

	@Bean
	public JwtFilter jwtFilter() {
		return new JwtFilter(jwtTokenProvider);
	}
}
