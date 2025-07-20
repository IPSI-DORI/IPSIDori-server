package com.server.dori.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class AuthBadRequestException extends CustomException {

	public AuthBadRequestException(HttpStatus httpStatus, String code, String message) {
		super(httpStatus, code, message);
	}

	public static AuthBadRequestException invalidKakaoAccessToken() {
		return new AuthBadRequestException(HttpStatus.BAD_REQUEST, "AUTH_400_1", "유효하지 않은 카카오 액세스 토큰입니다.");
	}
}
