package com.server.dori.global.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.server.dori.global.jwt.JwtFilter;
import com.server.dori.global.jwt.JwtTokenProvider;

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
