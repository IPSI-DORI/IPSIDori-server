package com.server.dori.domain.auth.service.implementation;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.server.dori.domain.auth.exception.AuthBadRequestException;
import com.server.dori.domain.auth.service.dto.KakaoUserInfo;
import com.server.dori.domain.auth.service.dto.KakaoUserMeResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoApiService {
	private static final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";

	private final RestTemplate restTemplate;

	public KakaoUserInfo getUserInfo(String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(accessToken);
		HttpEntity<Void> entity = new HttpEntity<>(headers);

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(KAKAO_USER_INFO_URL)
			.queryParam("property_keys", "[\"kakao_account.email\"]");

		try {
			ResponseEntity<KakaoUserMeResponse> response = restTemplate.exchange(
				uriBuilder.build().toUri(),
				HttpMethod.GET,
				entity,
				KakaoUserMeResponse.class
			);

			KakaoUserMeResponse body = response.getBody();
			if (body.kakaoAccount() == null || body.kakaoAccount().email() == null) {
				throw new IllegalStateException("카카오 사용자 정보 조회에 실패했습니다: 응답 본문 또는 이메일 정보가 없습니다.");
			}

			return new KakaoUserInfo(body.kakaoAccount().email());
		} catch (Exception e) {
			log.error("카카오 사용자 정보 조회 실패: {}", e.getMessage());
			throw AuthBadRequestException.invalidKakaoAccessToken();
		}
	}
}
