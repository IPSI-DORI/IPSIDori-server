package com.server.dori.domain.auth.service.implementation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.server.dori.domain.auth.service.dto.KakaoUserInfo;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthReader {
	private final MemberRepository memberRepository;

	public Optional<Member> findMemberByKakaoUserInfo(KakaoUserInfo userInfo) {
		return memberRepository.findByEmail(userInfo.email());
	}
}
