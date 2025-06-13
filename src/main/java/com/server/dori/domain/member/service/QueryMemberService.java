package com.server.dori.domain.member.service;

import org.springframework.stereotype.Service;

import com.server.dori.domain.member.entity.Member;
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
}
