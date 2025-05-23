package com.server.dori.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/v1/**")
			.allowedOrigins("https://localhost:3000",
				"https://ipsidori.o-r.kr")
			.allowedMethods("GET", "POST", "PUT", "DELETE")
			.allowedHeaders("*")
			.allowCredentials(true); // 쿠키
	}
}
