package com.server.dori.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.ErrorStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthErrorStatus implements ErrorStatus {
	INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_401_1", "유효하지 않은 토큰입니다."),
	EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_401_2", "만료된 토큰입니다."),
	INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_401_3", "유효하지 않은 리프레시 토큰입니다."),
	OAUTH_PROVIDER_NOT_FOUND(HttpStatus.BAD_REQUEST, "AUTH_400_1", "지원하지 않는 소셜 로그인입니다."),
	OAUTH_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH_404_1", "소셜 로그인 사용자 정보를 찾을 수 없습니다."),
	OAUTH_EMAIL_NOT_FOUND(HttpStatus.BAD_REQUEST, "AUTH_400_2", "소셜 로그인 사용자의 이메일 정보가 없습니다."),
	OAUTH_SIGNUP_REQUIRED(HttpStatus.BAD_REQUEST, "AUTH_400_3", "회원가입이 필요합니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
