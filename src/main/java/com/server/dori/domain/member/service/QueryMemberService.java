package com.server.dori.domain.member.service;

import org.springframework.stereotype.Service;

import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.MemberInfo;
import com.server.dori.domain.member.presentation.dto.response.MemberInfoDetailResponse;
import com.server.dori.domain.member.presentation.dto.response.MemberInfoResponse;
import com.server.dori.domain.member.presentation.dto.response.MemberSignupResponse;
import com.server.dori.domain.member.presentation.dto.response.MemberSignupResponse.CharacterInfo;
import com.server.dori.domain.member.service.implementation.MemberReader;
import com.server.dori.domain.member.service.implementation.MemberValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QueryMemberService {
	private final MemberReader memberReader;
	private final MemberValidator memberValidator;

	public Member getMemberInfo(Long memberId) {
		Member member = memberReader.getMember(memberId);
		memberValidator.validateMemberCompleted(member);
		return member;
	}

	public MemberInfoResponse createInfoResponse(Member member) {
		MemberInfo memberInfo = member.getMemberInfo();

		String nickname = memberReader.getNickname(member);
		String characterImageUrl = memberReader.getCharacterImageUrl(memberInfo);
		return new MemberInfoResponse(member.getId(), nickname, characterImageUrl);
	}

	public MemberInfoDetailResponse createInfoDetailResponse(Member member) {
		MemberInfo memberInfo = member.getMemberInfo();

		String nickname = memberReader.getNickname(member);
		String characterImageUrl = memberReader.getCharacterImageUrl(memberInfo);

		return new MemberInfoDetailResponse(
			member.getId(),
			nickname,
			memberInfo.getSchoolYear(),
			memberInfo.getTargetUniversity(),
			memberInfo.getTargetMajor(),
			characterImageUrl);
	}

	public MemberSignupResponse createSignupResponse(Member member) {
		MemberInfo memberInfo = member.getMemberInfo();

		String nickname = memberReader.getNickname(member);
		CharacterInfo characterInfo = memberReader.createCharacterInfo(memberInfo);

		return new MemberSignupResponse(
			member.getId(),
			member.getEmail(),
			nickname,
			characterInfo
		);
	}
}
