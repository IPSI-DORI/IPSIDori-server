package com.server.dori.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record KakaoLoginRequest(
	@NotBlank(message = "인가코드는 필수입니다.")
	String authorizationCode
) {
}
