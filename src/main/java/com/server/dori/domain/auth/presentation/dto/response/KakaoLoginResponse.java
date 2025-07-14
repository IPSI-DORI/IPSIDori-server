package com.server.dori.domain.auth.presentation.dto.response;

public record KakaoLoginResponse(
	boolean isSignupRequired,
	String message
) {
	public static KakaoLoginResponse signupRequired() {
		return new KakaoLoginResponse(true, "저희 서비스 회원이 아닙니다. 추가 정보를 입력해주세요.");
	}

	public static KakaoLoginResponse loginComplete() {
		return new KakaoLoginResponse(false, "로그인이 완료되었습니다.");
	}
}
