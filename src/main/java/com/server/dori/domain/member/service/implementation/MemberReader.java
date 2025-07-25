package com.server.dori.domain.member.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.server.dori.domain.member.entity.CustomUserDetails;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.MemberInfo;
import com.server.dori.domain.member.entity.sub.CharacterType;
import com.server.dori.domain.member.presentation.dto.response.MemberSignupResponse.CharacterInfo;
import com.server.dori.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberReader {
	private final MemberRepository memberRepository;

	@Value("${aws.s3.base-url}")
	private String s3BaseUrl;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.getByEmail(username);
		return new CustomUserDetails(member);
	}

	public Member getMemberByEmail(String email) {
		return memberRepository.getByEmail(email);
	}

	public Member getMember(Long memberId) {
		return memberRepository.getById(memberId);
	}

	public String getNickname(Member member) {
		return member.getMemberInfo().getNickname();
	}

	public String getCharacterImageUrl(MemberInfo memberInfo) {
		CharacterType characterType = CharacterType.fromScore(memberInfo.getLearningStyleScore());
		return characterType.getImageUrl(s3BaseUrl);
	}

	public CharacterInfo createCharacterInfo(MemberInfo memberInfo) {
		CharacterType characterType = CharacterType.fromScore(memberInfo.getLearningStyleScore());
		String imageUrl = getCharacterImageUrl(memberInfo);
		return new CharacterInfo(
			characterType.getName(),
			characterType.getCharacteristics(),
			imageUrl
		);
	}
}
