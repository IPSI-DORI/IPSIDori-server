package com.server.dori.domain.auth.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dori.domain.auth.presentation.dto.request.KakaoLoginRequest;
import com.server.dori.domain.auth.presentation.dto.request.TokenReissueRequest;
import com.server.dori.domain.auth.presentation.dto.response.TokenDto;
import com.server.dori.global.response.CustomApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/v1/auth")
@Tag(name = "인증", description = "인증/인가 관련 API")
public interface AuthApiController {

	@Operation(summary = "토큰 재발급")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "토큰 재발급 성공"),
		@ApiResponse(responseCode = "401", description = "유효하지 않은 리프레시 토큰")
	})
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping("/reissue")
	ResponseEntity<CustomApiResponse<TokenDto>> reissue(@RequestBody TokenReissueRequest requestDto);

	@Operation(summary = "카카오 로그인", description = "카카오 인가 코드를 받아 토큰을 발급합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "JWT 토큰 발급 성공"),
		@ApiResponse(responseCode = "400", description = "잘못된 요청")
	})
	@PostMapping("/oauth2/kakao")
	ResponseEntity<CustomApiResponse<TokenDto>> kakaoLogin(@RequestBody KakaoLoginRequest request);
}
