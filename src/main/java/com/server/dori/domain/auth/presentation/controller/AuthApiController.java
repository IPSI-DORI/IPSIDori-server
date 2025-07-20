package com.server.dori.domain.auth.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dori.domain.auth.presentation.dto.request.KakaoLoginRequest;
import com.server.dori.domain.auth.presentation.dto.request.TokenReissueRequest;
import com.server.dori.domain.auth.presentation.dto.response.KakaoLoginResponse;
import com.server.dori.global.response.CustomApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/v1/auth")
@Tag(name = "인증", description = "인증/인가 API")
public interface AuthApiController {

	@Operation(
		summary = "토큰 재발급",
		description = "리프레시 토큰으로 새로운 액세스 토큰을 발급합니다. "
			+ "Response Header에 Authorization(액세스 토큰)과 Refresh-Token(리프레시 토큰)이 포함됩니다."
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "토큰 재발급 성공"),
		@ApiResponse(responseCode = "401", description = "유효하지 않은 리프레시 토큰")
	})
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping("/reissue")
	ResponseEntity<CustomApiResponse<Void>> reissue(@RequestBody TokenReissueRequest requestDto);

	@Operation(
		summary = "카카오 로그인",
		description = "카카오 액세스 토큰을 받아 JWT 토큰을 발급합니다. "
			+ "Response Header에 Authorization(액세스 토큰)과 Refresh-Token(리프레시 토큰)이 포함되고, "
			+ "Response Body에 회원가입 완료 여부가 포함됩니다."
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "JWT 토큰 발급 성공"),
		@ApiResponse(responseCode = "400", description = "유효하지 않은 카카오 액세스 토큰"),
		@ApiResponse(responseCode = "404", description = "소셜 로그인 사용자 정보를 찾을 수 없음")
	})
	@PostMapping("/oauth2/kakao")
	ResponseEntity<CustomApiResponse<KakaoLoginResponse>> kakaoLogin(@RequestBody KakaoLoginRequest request);
}
