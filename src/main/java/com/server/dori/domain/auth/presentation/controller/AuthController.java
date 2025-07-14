package com.server.dori.domain.auth.presentation.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.server.dori.domain.auth.presentation.dto.request.KakaoLoginRequest;
import com.server.dori.domain.auth.presentation.dto.request.TokenReissueRequest;
import com.server.dori.domain.auth.presentation.dto.response.KakaoLoginResponse;
import com.server.dori.domain.auth.presentation.dto.response.TokenDto;
import com.server.dori.domain.auth.service.CommandAuthService;
import com.server.dori.domain.auth.service.QueryAuthService;
import com.server.dori.global.response.CustomApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApiController {
	private final QueryAuthService queryAuthService;
	private final CommandAuthService commandAuthService;

	@Override
	public ResponseEntity<CustomApiResponse<Void>> reissue(TokenReissueRequest requestDto) {
		TokenDto tokenDto = queryAuthService.reissue(requestDto.refreshToken());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", tokenDto.grantType() + " " + tokenDto.accessToken());
		headers.add("Refresh-Token", tokenDto.refreshToken());

		return ResponseEntity.ok()
			.headers(headers)
			.body(CustomApiResponse.ok().getBody());
	}

	@Override
	public ResponseEntity<CustomApiResponse<KakaoLoginResponse>> kakaoLogin(KakaoLoginRequest request) {
		CommandAuthService.KakaoLoginResult result = commandAuthService.kakaoLogin(request);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", result.tokenDto().grantType() + " " + result.tokenDto().accessToken());
		headers.add("Refresh-Token", result.tokenDto().refreshToken());

		return ResponseEntity.ok()
			.headers(headers)
			.body(CustomApiResponse.ok(result.loginResponse()).getBody());
	}
}
