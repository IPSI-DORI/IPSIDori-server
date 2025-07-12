package com.server.dori.domain.auth.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dori.domain.auth.presentation.dto.request.TokenReissueRequest;
import com.server.dori.domain.auth.presentation.dto.response.TokenDto;
import com.server.dori.domain.auth.service.QueryAuthService;
import com.server.dori.global.response.CustomApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "인증", description = "인증/인가 관련 API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	private final QueryAuthService queryAuthService;

	@Operation(summary = "토큰 재발급")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "토큰 재발급 성공"),
		@ApiResponse(responseCode = "401", description = "유효하지 않은 리프레시 토큰")
	})
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping("/reissue")
	public ResponseEntity<CustomApiResponse<TokenDto>> reissue(
		@Valid @RequestBody TokenReissueRequest requestDto
	) {
		return CustomApiResponse.ok(queryAuthService.reissue(requestDto.refreshToken()));
	}
}
