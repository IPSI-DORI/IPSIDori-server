package com.server.dori.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class AuthNotFoundException extends CustomException {

	public AuthNotFoundException(HttpStatus httpStatus, String code, String message) {
		super(httpStatus, code, message);
	}

	public static AuthNotFoundException oauthUserNotFound() {
		return new AuthNotFoundException(HttpStatus.NOT_FOUND, "AUTH_404_1", "소셜 로그인 사용자 정보를 찾을 수 없습니다.");
	}
}
