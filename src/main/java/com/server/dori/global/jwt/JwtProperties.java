package com.server.dori.global.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
@Validated
public class JwtProperties {
	@NotBlank(message = "JWT 시크릿 키는 필수 입니다.")
	private String secretKey;

	@NotNull(message = "액세스 토큰 유효시간은 필수입니다.")
	@Min(value = 1, message = "액세스 토큰 유효시간은 1초 이상입니다.")
	private long accessTokenValidityInMilliseconds;

	@NotNull(message = "리프레시 토큰 유효시간은 필수입니다.")
	@Min(value = 1, message = "리프레시 토큰 유효시간은 최소 1초 이상입니다.")
	private long refreshTokenValidityInMilliseconds;
}
