package com.server.dori.domain.auth.presentation.dto.response;

import jakarta.validation.constraints.NotNull;

public record TokenDto(
	@NotNull String grantType,
	@NotNull String accessToken,
	@NotNull String refreshToken
) {
	public static TokenDto of(String grantType, String accessToken, String refreshToken) {
		return new TokenDto(grantType, accessToken, refreshToken);
	}
}
