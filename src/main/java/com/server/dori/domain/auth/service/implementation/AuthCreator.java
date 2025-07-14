package com.server.dori.domain.auth.service.implementation;

import org.springframework.stereotype.Service;

import com.server.dori.domain.auth.service.dto.KakaoUserInfo;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.sub.Role;
import com.server.dori.domain.member.entity.sub.SocialType;
import com.server.dori.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthCreator {
	private final MemberRepository memberRepository;

	public Member createMemberByKakaoUserInfo(KakaoUserInfo userInfo) {
		Member member = Member.builder()
			.email(userInfo.email())
			.socialType(SocialType.KAKAO)
			.role(Role.USER)
			.build();
		return memberRepository.save(member);
	}
}
