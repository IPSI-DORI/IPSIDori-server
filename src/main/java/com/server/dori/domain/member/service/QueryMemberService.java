package com.server.dori.domain.member.service;

import org.springframework.stereotype.Service;

import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.MemberInfo;
import com.server.dori.domain.member.presentation.dto.response.MemberInfoDetailResponseDto;
import com.server.dori.domain.member.presentation.dto.response.MemberInfoResponseDto;
import com.server.dori.domain.member.presentation.dto.response.MemberSignupResponseDto;
import com.server.dori.domain.member.presentation.dto.response.MemberSignupResponseDto.CharacterInfo;
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

	public MemberInfoResponseDto createInfoResponse(Member member) {
		String nickname = memberReader.getNickname(member);
		String characterImageUrl = memberReader.getCharacterImageUrl(member.getMemberInfo());
		return new MemberInfoResponseDto(member.getId(), nickname, characterImageUrl);
	}

	public MemberInfoDetailResponseDto createInfoDetailResponse(Member member) {
		String nickname = memberReader.getNickname(member);
		String characterImageUrl = memberReader.getCharacterImageUrl(member.getMemberInfo());
		MemberInfo memberInfo = member.getMemberInfo();

		return new MemberInfoDetailResponseDto(
			member.getId(),
			nickname,
			memberInfo != null ? memberInfo.getGrade() : null,
			memberInfo != null ? memberInfo.getTargetUniversity() : null,
			memberInfo != null ? memberInfo.getTargetMajor() : null,
			characterImageUrl);
	}

	public MemberSignupResponseDto createSignupResponse(Member member) {
		String nickname = memberReader.getNickname(member);
		CharacterInfo characterInfo = memberReader.createCharacterInfo(member.getMemberInfo());

		return new MemberSignupResponseDto(
			member.getId(),
			member.getEmail(),
			nickname,
			characterInfo
		);
	}
}
