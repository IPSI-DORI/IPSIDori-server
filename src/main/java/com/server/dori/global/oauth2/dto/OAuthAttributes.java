package com.server.dori.global.oauth2.dto;

import java.util.Map;

import com.server.dori.domain.auth.exception.AuthErrorStatus;
import com.server.dori.domain.auth.exception.AuthException;
import com.server.dori.global.oauth2.userinfo.KakaoOAuth2UserInfo;
import com.server.dori.global.oauth2.userinfo.OAuth2UserInfo;

public record OAuthAttributes(
	Map<String, Object> attributes,
	String nameAttributeKey,
	OAuth2UserInfo oAuth2UserInfo
) {
	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
		Map<String, Object> attributes) {
		if ("kakao".equals(registrationId)) {
			return ofKakao(userNameAttributeName, attributes);
		}

		throw new AuthException(AuthErrorStatus.OAUTH_PROVIDER_NOT_FOUND);
	}

	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		return new OAuthAttributes(
			attributes,
			userNameAttributeName,
			new KakaoOAuth2UserInfo(attributes)
		);
	}
}
