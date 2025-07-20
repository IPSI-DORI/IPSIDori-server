package com.server.dori.domain.auth.service.dto;

import com.server.dori.domain.auth.presentation.dto.response.KakaoLoginResponse;
import com.server.dori.domain.auth.presentation.dto.response.TokenDto;

public record KakaoLoginResult(
	TokenDto tokenDto,
	KakaoLoginResponse loginResponse
) {
}
