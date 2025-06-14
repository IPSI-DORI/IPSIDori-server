package com.server.dori.domain.member.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.member.entity.CustomUserDetails;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.MemberInfo;
import com.server.dori.domain.member.entity.sub.CharacterType;
import com.server.dori.domain.member.exception.MemberNotFoundException;
import com.server.dori.domain.member.presentation.dto.response.MemberSignupResponseDto.CharacterInfo;
import com.server.dori.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberReader {
	private final MemberRepository memberRepository;

	@Value("${aws.s3.base-url}")
	private String s3BaseUrl;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(username)
			.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
		return new CustomUserDetails(member);
	}

	public Member getMemberByEmail(String email) {
		return memberRepository.findByEmail(email)
			.orElseThrow(MemberNotFoundException::memberNotFoundException);
	}

	public Member getMember(Long memberId) {
		return memberRepository.findById(memberId)
			.orElseThrow(MemberNotFoundException::memberNotFoundException);
	}

	public String getNickname(Member member) {
		return member.getMemberInfo() != null && member.getMemberInfo().getNickname() != null
			? member.getMemberInfo().getNickname()
			: member.getNickname();
	}

	public String getCharacterImageUrl(MemberInfo memberInfo) {
		if (memberInfo == null || !CharacterType.isValidScore(memberInfo.getLearningStyleScore())) {
			return String.format("%s/default.png", s3BaseUrl);
		}
		CharacterType characterType = CharacterType.fromScore(memberInfo.getLearningStyleScore());
		return characterType.getImageUrl(s3BaseUrl);
	}

	public CharacterInfo createCharacterInfo(MemberInfo memberInfo) {
		if (memberInfo == null || !CharacterType.isValidScore(memberInfo.getLearningStyleScore())) {
			return null;
		}
		CharacterType characterType = CharacterType.fromScore(memberInfo.getLearningStyleScore());
		String imageUrl = characterType.getImageUrl(s3BaseUrl);
		return new CharacterInfo(
			characterType.getName(),
			characterType.getCharacteristics(),
			imageUrl
		);
	}
}
