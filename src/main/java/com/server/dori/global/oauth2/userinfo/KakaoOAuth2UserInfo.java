package com.server.dori.global.oauth2.userinfo;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class KakaoOAuth2UserInfo implements OAuth2UserInfo {
	private static final String KAKAO_ACCOUNT = "kakao_account";
	private static final String PROFILE = "profile";
	private static final String EMAIL = "email";
	private static final String NICKNAME = "nickname";
	private static final String ID = "id";

	private final Map<String, Object> attributes;
	private final Map<String, Object> kakaoAccount;
	private final Map<String, Object> profile;

	public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
		this.kakaoAccount = Optional.ofNullable(attributes.get(KAKAO_ACCOUNT))
			.filter(Map.class::isInstance)
			.map(map -> (Map<String, Object>) map)
			.orElse(Collections.emptyMap());
		this.profile = Optional.ofNullable(attributes.get(PROFILE))
			.filter(Map.class::isInstance)
			.map(map -> (Map<String, Object>) map)
			.orElse(Collections.emptyMap());
	}

	@Override
	public String getId() {
		return String.valueOf(attributes.get(ID));
	}

	@Override
	public String getEmail() {
		String email = (String)kakaoAccount.get(EMAIL);
		// 이메일이 없는 경우 카카오 ID를 이메일로 사용
		return Objects.requireNonNullElseGet(email, () -> "kakao_" + getId() + "@kakao.com");
	}

	@Override
	public String getNickname() {
		String nickname = (String)profile.get(NICKNAME);
		if (nickname == null) {
			// 닉네임이 없는 경우 기본값 사용
			return "카카오사용자_" + getId();
		}
		return nickname;
	}
}
