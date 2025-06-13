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

import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.exception.MemberNotFoundException;
import com.server.dori.domain.member.presentation.dto.request.MemberInfoUpdateDto;
import com.server.dori.domain.member.presentation.dto.request.MemberSignupRequestDto;
import com.server.dori.domain.member.presentation.dto.response.MemberInfoResponseDto;
import com.server.dori.domain.member.presentation.dto.response.MemberSignupResponseDto;
import com.server.dori.domain.member.presentation.dto.response.MemberSimpleResponseDto;
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
	public ResponseEntity<ApiResponseDto<MemberSignupResponseDto>> signup(
		@Parameter(description = "현재 로그인한 사용자 정보")
		@AuthenticationPrincipal UserDetails userDetails,
		@Valid @RequestBody MemberSignupRequestDto requestDto
	) {
		if (userDetails == null) {
			throw MemberNotFoundException.memberNotFoundException();
		}

		Member updatedMember = commandMemberService.signup(userDetails.getUsername(), requestDto);
		return ApiResponseDto.ok(MemberSignupResponseDto.from(updatedMember));
	}

	@Operation(
		summary = "회원 기본 정보",
		description = "회원 기본 정보를 조회합니다. (닉네임만 반환)"
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "조회 성공"),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
		@ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음"),
		@ApiResponse(responseCode = "400", description = "회원가입이 완료되지 않음")
	})
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping("/info")
	public ResponseEntity<ApiResponseDto<MemberSimpleResponseDto>> getMemberInfo(
		@Parameter(description = "현재 로그인한 사용자 정보")
		@AuthenticationPrincipal UserDetails userDetails
	) {
		if (userDetails == null) {
			throw MemberNotFoundException.memberNotFoundException();
		}

		Member member = queryMemberService.getMemberInfoByEmail(userDetails.getUsername());
		return ApiResponseDto.ok(MemberSimpleResponseDto.from(member));
	}

	@Operation(
		summary = "회원 상세 정보",
		description = "회원 상세 정보를 조회합니다. (닉네임, 학년, 희망 대학교, 희망 전공)"
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "조회 성공"),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
		@ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음"),
		@ApiResponse(responseCode = "400", description = "회원가입이 완료되지 않음")
	})
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping("/info/detail")
	public ResponseEntity<ApiResponseDto<MemberInfoResponseDto>> getMemberInfoDetail(
		@Parameter(description = "현재 로그인한 사용자 정보")
		@AuthenticationPrincipal UserDetails userDetails
	) {
		if (userDetails == null) {
			throw MemberNotFoundException.memberNotFoundException();
		}
		Member member = queryMemberService.getMemberInfoByEmail(userDetails.getUsername());
		return ApiResponseDto.ok(MemberInfoResponseDto.from(member));
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
	public ResponseEntity<ApiResponseDto<MemberInfoResponseDto>> updateMemberInfo(
		@Parameter(description = "현재 로그인한 사용자 정보")
		@AuthenticationPrincipal UserDetails userDetails,
		@Valid @RequestBody MemberInfoUpdateDto requestDto
	) {
		if (userDetails == null) {
			throw MemberNotFoundException.memberNotFoundException();
		}

		Member updatedMember = commandMemberService.updateMemberInfo(
			userDetails.getUsername(),
			requestDto
		);
		return ApiResponseDto.ok(MemberInfoResponseDto.from(updatedMember));
	}
}
