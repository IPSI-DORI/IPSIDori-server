package com.server.dori.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class AuthUnauthorizedException extends CustomException {

	public AuthUnauthorizedException(HttpStatus httpStatus, String code, String message) {
		super(httpStatus, code, message);
	}

	public static AuthUnauthorizedException invalidToken() {
		return new AuthUnauthorizedException(HttpStatus.UNAUTHORIZED, "AUTH_401_1", "유효하지 않은 토큰입니다.");
	}

	public static AuthUnauthorizedException expiredToken() {
		return new AuthUnauthorizedException(HttpStatus.UNAUTHORIZED, "AUTH_401_2", "만료된 토큰입니다.");
	}
}
