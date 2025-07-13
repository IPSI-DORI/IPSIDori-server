package com.server.dori.domain.auth.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoUserMeResponse(
	@JsonProperty("kakao_account")
	KakaoAccount kakaoAccount
) {
	public record KakaoAccount(
		String email
	) {
	}
}
