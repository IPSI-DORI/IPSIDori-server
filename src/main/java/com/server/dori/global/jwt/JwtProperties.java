package com.server.dori.global.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "jwt")
@Validated
public record JwtProperties(
	@NotBlank(message = "JWT 시크릿 키는 필수 입니다.")
	String secretKey,

	@NotNull(message = "액세스 토큰 유효시간은 필수입니다.")
	@Min(value = 1, message = "액세스 토큰 유효시간은 1초 이상입니다.")
	long accessTokenValidityInMilliseconds,

	@NotNull(message = "리프레시 토큰 유효시간은 필수입니다.")
	@Min(value = 1, message = "리프레시 토큰 유효시간은 최소 1초 이상입니다.")
	long refreshTokenValidityInMilliseconds
) {
	@ConstructorBinding
	public JwtProperties {
	}
}
