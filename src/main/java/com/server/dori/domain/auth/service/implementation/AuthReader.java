package com.server.dori.domain.auth.service.implementation;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.auth.exception.AuthNotFoundException;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.sub.SocialType;
import com.server.dori.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthReader {
	private final MemberRepository memberRepository;

	@Transactional(readOnly = true)
	public Member findMemberByOAuth2User(OAuth2User oauth2User) {
		String socialId = oauth2User.getAttribute("id").toString();
		return memberRepository.findBySocialTypeAndSocialId(SocialType.KAKAO, socialId)
			.orElseThrow(AuthNotFoundException::oauthUserNotFound);
	}
}
