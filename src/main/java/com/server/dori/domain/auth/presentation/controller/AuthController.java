package com.server.dori.domain.auth.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dori.domain.auth.exception.AuthErrorStatus;
import com.server.dori.domain.auth.exception.AuthException;
import com.server.dori.domain.auth.presentation.dto.TokenDto;
import com.server.dori.domain.auth.service.AuthService;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.presentation.dto.MemberSignupRequestDto;
import com.server.dori.domain.member.service.MemberService;
import com.server.dori.global.response.ApiResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "인증", description = "인증 관련 API")
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	private final MemberService memberService;

	@Operation(summary = "카카오 소셜 로그인")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "로그인 성공"),
		@ApiResponse(responseCode = "401", description = "카카오 인증 실패"),
		@ApiResponse(responseCode = "404", description = "카카오 사용자 정보 없음")
	})
	@GetMapping("/oauth2/kakao")
	@Transactional(readOnly = true)
	public ResponseEntity<ApiResponseDto<TokenDto>> kakaoLogin(
		@AuthenticationPrincipal OAuth2User oauth2User
	) {
		if (oauth2User == null) {
			throw new AuthException(AuthErrorStatus.OAUTH_USER_NOT_FOUND);
		}
		return ApiResponseDto.ok(authService.oauth2Login(oauth2User));
	}

	@Operation(summary = "카카오 회원가입")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "회원가입 성공"),
		@ApiResponse(responseCode = "400", description = "잘못된 요청"),
		@ApiResponse(responseCode = "409", description = "이미 가입된 회원")
	})
	@PostMapping("/signup")
	@Transactional
	public ResponseEntity<ApiResponseDto<TokenDto>> signup(
		@RequestBody MemberSignupRequestDto requestDto
	) {
		Member member = memberService.signupWithAdditionalInfo(requestDto);
		return ApiResponseDto.ok(authService.createToken(member));
	}

	@Operation(summary = "토큰 재발급")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "토큰 재발급 성공"),
		@ApiResponse(responseCode = "401", description = "유효하지 않은 리프레시 토큰")
	})
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping("/reissue")
	public ResponseEntity<ApiResponseDto<TokenDto>> reissue(
		@RequestBody String refreshToken
	) {
		return ApiResponseDto.ok(authService.reissue(refreshToken));
	}
}
