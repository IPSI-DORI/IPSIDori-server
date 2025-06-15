package com.server.dori.domain.auth.presentation.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dori.domain.auth.exception.AuthNotFoundException;
import com.server.dori.domain.auth.presentation.dto.request.TokenReissueRequestDto;
import com.server.dori.domain.auth.presentation.dto.response.TokenDto;
import com.server.dori.domain.auth.service.QueryAuthService;
import com.server.dori.global.response.ApiResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "인증", description = "인증/인가 관련 API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	private final QueryAuthService queryAuthService;

	@Operation(summary = "카카오 로그인 시작", description = "카카오 OAuth2 로그인 페이지로 리다이렉트합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "302", description = "카카오 로그인 페이지로 리다이렉트")
	})
	@GetMapping("/oauth2/authorization/kakao")
	public void kakaoLoginStart(HttpServletResponse response) throws IOException {
		response.sendRedirect("/oauth2/authorization/kakao");
	}

	@Operation(summary = "카카오 소셜 로그인 콜백", description = "카카오 OAuth2 인증 완료 후 호출되는 콜백 엔드포인트입니다. JWT 토큰을 발급받습니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "로그인 성공, JWT 토큰 발급"),
		@ApiResponse(responseCode = "404", description = "카카오 사용자 정보 없음")
	})
	@GetMapping("/oauth2/kakao")
	public ResponseEntity<ApiResponseDto<TokenDto>> kakaoLoginCallback(
		@Parameter(hidden = true) @AuthenticationPrincipal OAuth2User oauth2User
	) {
		if (oauth2User == null) {
			throw AuthNotFoundException.oauthUserNotFound();
		}
		return ApiResponseDto.ok(queryAuthService.oauth2Login(oauth2User));
	}

	@Operation(summary = "토큰 재발급")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "토큰 재발급 성공"),
		@ApiResponse(responseCode = "401", description = "유효하지 않은 리프레시 토큰")
	})
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping("/reissue")
	public ResponseEntity<ApiResponseDto<TokenDto>> reissue(
		@Valid @RequestBody TokenReissueRequestDto requestDto
	) {
		return ApiResponseDto.ok(queryAuthService.reissue(requestDto.refreshToken()));
	}
}
