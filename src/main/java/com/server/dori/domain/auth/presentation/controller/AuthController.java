package com.server.dori.domain.auth.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.server.dori.domain.auth.presentation.dto.request.KakaoLoginRequest;
import com.server.dori.domain.auth.presentation.dto.request.TokenReissueRequest;
import com.server.dori.domain.auth.presentation.dto.response.TokenDto;
import com.server.dori.domain.auth.service.QueryAuthService;
import com.server.dori.global.response.CustomApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApiController {
	private final QueryAuthService queryAuthService;

	@Override
	public ResponseEntity<CustomApiResponse<TokenDto>> reissue(TokenReissueRequest requestDto) {
		return CustomApiResponse.ok(queryAuthService.reissue(requestDto.refreshToken()));
	}

	@Override
	public ResponseEntity<CustomApiResponse<TokenDto>> kakaoLogin(KakaoLoginRequest request) {
		TokenDto tokenDto = queryAuthService.kakaoLogin(request.accessToken());
		return CustomApiResponse.ok(tokenDto);
	}
}
