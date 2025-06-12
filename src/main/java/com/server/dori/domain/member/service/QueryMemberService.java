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

	public Member getMemberByEmail(String email) {
		return memberReader.getMemberByEmail(email);
	}

	public Member getMyInfoByEmail(String email) {
		Member member = memberReader.getMemberByEmail(email);
		memberValidator.validateMemberCompleted(member);
		return member;
	}
}
