package com.server.dori.domain.member.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.server.dori.global.response.CustomApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController implements MemberApiController {

	private final QueryMemberService queryMemberService;
	private final CommandMemberService commandMemberService;

	public ResponseEntity<CustomApiResponse<MemberSignupResponse>> signup(
		@AuthenticationPrincipal UserDetails userDetails,
		MemberSignupRequest requestDto
	) {
		if (userDetails == null) {
			throw new MemberNotFoundException();
		}

		Member updatedMember = commandMemberService.signup(userDetails.getUsername(), requestDto);
		return CustomApiResponse.ok(queryMemberService.createSignupResponse(updatedMember));
	}

	public ResponseEntity<CustomApiResponse<MemberInfoResponse>> getMemberInfo(
		@AuthenticationPrincipal CustomUserDetails userDetails
	) {
		if (userDetails == null) {
			throw new MemberNotFoundException();
		}

		Member member = queryMemberService.getMemberInfo(userDetails.getMemberId());
		return CustomApiResponse.ok(queryMemberService.createInfoResponse(member));
	}

	public ResponseEntity<CustomApiResponse<MemberInfoDetailResponse>> getMemberInfoDetail(
		@AuthenticationPrincipal CustomUserDetails userDetails
	) {
		if (userDetails == null) {
			throw new MemberNotFoundException();
		}

		Member member = queryMemberService.getMemberInfo(userDetails.getMemberId());
		return CustomApiResponse.ok(queryMemberService.createInfoDetailResponse(member));
	}

	public ResponseEntity<CustomApiResponse<MemberInfoDetailResponse>> updateMemberInfo(
		@AuthenticationPrincipal CustomUserDetails userDetails,
		MemberInfoUpdate requestDto
	) {
		if (userDetails == null) {
			throw new MemberNotFoundException();
		}

		Member updatedMember = commandMemberService.updateMemberInfo(
			userDetails.getMemberId(),
			requestDto
		);
		return CustomApiResponse.ok(queryMemberService.createInfoDetailResponse(updatedMember));
	}
}
