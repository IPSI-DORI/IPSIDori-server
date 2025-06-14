package com.server.dori.global.oauth2;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.sub.Role;
import com.server.dori.domain.member.entity.sub.SocialType;
import com.server.dori.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	private static final String KAKAO_REGISTRATION_ID = "kakao";
	private static final String ROLE_PREFIX = "ROLE_";
	private final MemberRepository memberRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);

		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
			.getUserInfoEndpoint().getUserNameAttributeName();

		Map<String, Object> attributes = oAuth2User.getAttributes();

		OAuth2Attributes extractAttributes = OAuth2Attributes.of(registrationId, userNameAttributeName, attributes);

		Member member = getUser(extractAttributes, registrationId);

		return new DefaultOAuth2User(
			Collections.singleton(new SimpleGrantedAuthority(ROLE_PREFIX + member.getRole().name())),
			extractAttributes.attributes(),
			extractAttributes.nameAttributeKey());
	}

	private Member getUser(OAuth2Attributes attributes, String registrationId) {
		SocialType socialType = getSocialType(registrationId);
		String socialId = attributes.oAuth2UserInfo().getId();

		Optional<Member> findMember = memberRepository.findBySocialTypeAndSocialId(socialType, socialId);

		return findMember.orElseGet(() -> createTemporaryMember(attributes, socialType, socialId));
	}

	private SocialType getSocialType(String registrationId) {
		if (KAKAO_REGISTRATION_ID.equals(registrationId)) {
			return SocialType.KAKAO;
		}
		return SocialType.NONE;
	}

	private Member createTemporaryMember(OAuth2Attributes attributes, SocialType socialType, String socialId) {
		// 임시 회원 생성 (추가 정보 없음)
		Member member = Member.builder()
			.email(attributes.oAuth2UserInfo().getEmail())
			.nickname(attributes.oAuth2UserInfo().getNickname())
			.socialType(socialType)
			.socialId(socialId)
			.role(Role.USER)
			.build();

		return memberRepository.save(member);
	}
}
