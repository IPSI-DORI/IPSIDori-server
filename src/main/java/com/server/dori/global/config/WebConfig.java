package com.server.dori.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/v1/**")
			.allowedOrigins("http://localhost:3000", "http://localhost:8080", "https://ipsidori.o-r.kr")
			.allowedMethods("GET", "POST", "PUT", "DELETE")
			.allowedHeaders("*")
			.allowCredentials(true); // 쿠키

		// Swagger UI 관련 CORS 설정
		registry.addMapping("/swagger-ui/**")
			.allowedOrigins("*")
			.allowedMethods("GET", "POST", "PUT", "DELETE")
			.allowedHeaders("*");

		registry.addMapping("/v3/api-docs/**")
			.allowedOrigins("*")
			.allowedMethods("GET")
			.allowedHeaders("*");
	}
}
