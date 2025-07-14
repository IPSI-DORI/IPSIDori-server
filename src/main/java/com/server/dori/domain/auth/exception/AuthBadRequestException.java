package com.server.dori.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class AuthBadRequestException extends CustomException {

	public AuthBadRequestException(HttpStatus httpStatus, String code, String message) {
		super(httpStatus, code, message);
	}

	public static AuthBadRequestException oauthProviderNotFound() {
		return new AuthBadRequestException(HttpStatus.BAD_REQUEST, "AUTH_400_1", "카카오 인증 처리 중 오류가 발생했습니다.");
	}

	public static AuthBadRequestException invalidAuthorizationCode() {
		return new AuthBadRequestException(HttpStatus.BAD_REQUEST, "AUTH_400_2", "유효하지 않은 인가코드입니다.");
	}
}
