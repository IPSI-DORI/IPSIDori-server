package com.server.dori.global.jwt;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {

	@Bean
	public JwtFilter jwtFilter(JwtTokenProvider jwtTokenProvider) {
		return new JwtFilter(jwtTokenProvider);
	}
}