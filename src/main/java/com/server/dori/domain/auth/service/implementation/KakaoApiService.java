package com.server.dori.domain.auth.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.server.dori.domain.auth.exception.AuthBadRequestException;
import com.server.dori.domain.auth.exception.AuthNotFoundException;
import com.server.dori.domain.auth.service.dto.KakaoTokenResponse;
import com.server.dori.domain.auth.service.dto.KakaoUserInfo;
import com.server.dori.domain.auth.service.dto.KakaoUserMeResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KakaoApiService {
	private static final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
	private static final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";

	private final RestTemplate restTemplate;

	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String clientId;

	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	private String redirectUri;

	public KakaoUserInfo getUserInfo(String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(accessToken);
		HttpEntity<Void> entity = new HttpEntity<>(headers);
		try {
			ResponseEntity<KakaoUserMeResponse> response = restTemplate.exchange(
				KAKAO_USER_INFO_URL,
				HttpMethod.GET,
				entity,
				KakaoUserMeResponse.class
			);
			KakaoUserMeResponse body = response.getBody();
			if (body.kakaoAccount() == null) {
				throw AuthNotFoundException.oauthUserNotFound();
			}
			String email = body.kakaoAccount().email();
			return new KakaoUserInfo(email);
		} catch (Exception e) {
			throw AuthBadRequestException.oauthProviderNotFound();
		}
	}

	public String getAccessTokenFromAuthorizationCode(String authorizationCode) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", clientId);
		params.add("redirect_uri", redirectUri);
		params.add("code", authorizationCode);

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

		try {
			ResponseEntity<KakaoTokenResponse> response = restTemplate.exchange(
				KAKAO_TOKEN_URL,
				HttpMethod.POST,
				entity,
				KakaoTokenResponse.class
			);

			KakaoTokenResponse body = response.getBody();
			if (body.accessToken() == null) {
				throw AuthBadRequestException.invalidAuthorizationCode();
			}

			return body.accessToken();
		} catch (Exception e) {
			throw AuthBadRequestException.invalidAuthorizationCode();
		}
	}
}
