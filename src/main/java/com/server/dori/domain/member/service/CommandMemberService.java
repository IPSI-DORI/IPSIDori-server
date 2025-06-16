package com.server.dori.domain.member.service;

import org.springframework.stereotype.Service;

import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.presentation.dto.request.MemberInfoUpdate;
import com.server.dori.domain.member.presentation.dto.request.MemberSignupRequest;
import com.server.dori.domain.member.service.implementation.MemberReader;
import com.server.dori.domain.member.service.implementation.MemberUpdater;
import com.server.dori.domain.member.service.implementation.MemberValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommandMemberService {
	private final MemberUpdater memberUpdater;
	private final MemberValidator memberValidator;
	private final MemberReader memberReader;

	public Member signup(String email, MemberSignupRequest requestDto) {
		Member member = memberReader.getMemberByEmail(email);
		memberValidator.validateSignupRequest(member, requestDto);
		return memberUpdater.updateMemberWithAdditionalInfo(member, requestDto);
	}

	public Member updateMemberInfo(Long memberId, MemberInfoUpdate requestDto) {
		Member member = memberReader.getMember(memberId);
		return memberUpdater.updateInfo(member, requestDto);
	}
}
