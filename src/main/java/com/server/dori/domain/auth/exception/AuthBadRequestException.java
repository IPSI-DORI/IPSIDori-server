package com.server.dori.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class AuthBadRequestException extends CustomException {

	public AuthBadRequestException(HttpStatus httpStatus, String code, String message) {
		super(httpStatus, code, message);
	}

	public static AuthBadRequestException oauthProviderNotFound() {
		return new AuthBadRequestException(HttpStatus.BAD_REQUEST, "AUTH_400_1", "지원하지 않는 소셜 로그인입니다.");
	}
}
