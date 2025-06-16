package com.server.dori.domain.member.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dori.domain.member.entity.CustomUserDetails;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.exception.MemberNotFoundException;
import com.server.dori.domain.member.presentation.dto.request.MemberInfoUpdate;
import com.server.dori.domain.member.presentation.dto.request.MemberSignupRequest;
import com.server.dori.domain.member.presentation.dto.response.MemberInfoDetailResponse;
import com.server.dori.domain.member.presentation.dto.response.MemberInfoResponse;
import com.server.dori.domain.member.presentation.dto.response.MemberSignupResponse;
import com.server.dori.domain.member.service.CommandMemberService;
import com.server.dori.domain.member.service.QueryMemberService;
import com.server.dori.global.response.ApiResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "회원", description = "회원 관련 API")
@Slf4j
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

	private final QueryMemberService queryMemberService;
	private final CommandMemberService commandMemberService;

	@Operation(
		summary = "회원가입 (추가 정보 입력)",
		description = "OAuth2 로그인 후 발급받은 토큰으로 추가 정보를 입력하여 회원가입을 완료합니다."
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "회원가입 성공"),
		@ApiResponse(responseCode = "400", description = "잘못된 요청"),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
		@ApiResponse(responseCode = "409", description = "이미 가입된 회원")
	})
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping("/signup")
	public ResponseEntity<ApiResponseDto<MemberSignupResponse>> signup(
		@Parameter(description = "현재 로그인한 사용자 정보")
		@AuthenticationPrincipal UserDetails userDetails,
		@Valid @RequestBody MemberSignupRequest requestDto
	) {
		if (userDetails == null) {
			throw MemberNotFoundException.memberNotFoundException();
		}

		Member updatedMember = commandMemberService.signup(userDetails.getUsername(), requestDto);
		return ApiResponseDto.ok(queryMemberService.createSignupResponse(updatedMember));
	}

	@Operation(
		summary = "회원 기본 정보",
		description = "회원 기본 정보를 조회합니다. (닉네임, 프로필 이미지 URL)"
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "조회 성공"),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
		@ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음"),
		@ApiResponse(responseCode = "400", description = "회원가입이 완료되지 않음")
	})
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping("/info")
	public ResponseEntity<ApiResponseDto<MemberInfoResponse>> getMemberInfo(
		@Parameter(description = "현재 로그인한 사용자 정보")
		@AuthenticationPrincipal CustomUserDetails userDetails
	) {
		if (userDetails == null) {
			throw MemberNotFoundException.memberNotFoundException();
		}

		Member member = queryMemberService.getMemberInfo(userDetails.getMemberId());
		return ApiResponseDto.ok(queryMemberService.createInfoResponse(member));
	}

	@Operation(
		summary = "회원 상세 정보",
		description = "회원 상세 정보를 조회합니다. (닉네임, 학년, 희망 대학교, 희망 전공, 프로필 이미지 URL)"
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "조회 성공"),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
		@ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음"),
		@ApiResponse(responseCode = "400", description = "회원가입이 완료되지 않음")
	})
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping("/info/detail")
	public ResponseEntity<ApiResponseDto<MemberInfoDetailResponse>> getMemberInfoDetail(
		@Parameter(description = "현재 로그인한 사용자 정보")
		@AuthenticationPrincipal CustomUserDetails userDetails
	) {
		if (userDetails == null) {
			throw MemberNotFoundException.memberNotFoundException();
		}

		Member member = queryMemberService.getMemberInfo(userDetails.getMemberId());
		return ApiResponseDto.ok(queryMemberService.createInfoDetailResponse(member));
	}

	@Operation(
		summary = "회원 정보 수정",
		description = "회원 정보를 수정합니다. (닉네임, 학년, 희망 대학교, 희망 전공)"
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "수정 성공"),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
		@ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
	})
	@SecurityRequirement(name = "bearerAuth")
	@PutMapping("/info")
	public ResponseEntity<ApiResponseDto<MemberInfoDetailResponse>> updateMemberInfo(
		@Parameter(description = "현재 로그인한 사용자 정보")
		@AuthenticationPrincipal CustomUserDetails userDetails,
		@Valid @RequestBody MemberInfoUpdate requestDto
	) {
		if (userDetails == null) {
			throw MemberNotFoundException.memberNotFoundException();
		}

		Member updatedMember = commandMemberService.updateMemberInfo(
			userDetails.getMemberId(),
			requestDto
		);
		return ApiResponseDto.ok(queryMemberService.createInfoDetailResponse(updatedMember));
	}
}
