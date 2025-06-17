package com.server.dori.global.config;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Value("${spring.profiles.active:dev}")
	private String activeProfile;

	@Bean
	public OpenAPI openApi() {
		Info info = new Info()
			.title("IPSI API Documentation")
			.description("IPSI 서비스의 API 문서입니다.")
			.version("v1.0.0");

		// JWT 인증 설정
		SecurityScheme securityScheme = new SecurityScheme()
			.type(SecurityScheme.Type.HTTP)
			.scheme("bearer")
			.bearerFormat("JWT")
			.in(SecurityScheme.In.HEADER)
			.name("Authorization");

		SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

		// 프로파일에 따른 서버 설정
		List<Server> servers = getServersByProfile();

		return new OpenAPI()
			.info(info)
			.components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
			.addSecurityItem(securityRequirement)
			.servers(servers);
	}

	private List<Server> getServersByProfile() {
		if ("prod".equals(activeProfile)) {
			// 프로덕션 : HTTPS
			return Collections.singletonList(
				new Server().url("https://ipsidori.o-r.kr").description("Production Server (HTTPS)")
			);
		} else {
			// 개발/테스트: 로컬호스트
			return Collections.singletonList(
				new Server().url("http://localhost:8080").description("Local Development Server")
			);
		}
	}
}
