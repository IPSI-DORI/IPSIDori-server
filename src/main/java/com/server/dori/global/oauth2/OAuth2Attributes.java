package com.server.dori.global.oauth2;

import java.util.Map;

import com.server.dori.domain.auth.exception.AuthBadRequestException;
import com.server.dori.global.oauth2.provider.KakaoOAuth2UserInfo;
import com.server.dori.global.oauth2.provider.OAuth2UserInfo;

public record OAuth2Attributes(
	Map<String, Object> attributes,
	String nameAttributeKey,
	OAuth2UserInfo oAuth2UserInfo
) {
	public static OAuth2Attributes of(String registrationId, String userNameAttributeName,
		Map<String, Object> attributes) {
		if ("kakao".equals(registrationId)) {
			return ofKakao(userNameAttributeName, attributes);
		}

		throw AuthBadRequestException.oauthProviderNotFound();
	}

	private static OAuth2Attributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		return new OAuth2Attributes(
			attributes,
			userNameAttributeName,
			new KakaoOAuth2UserInfo(attributes)
		);
	}
}
