package com.server.dori.global.response.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorStatus implements ErrorStatus {

	BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON_400", "잘못된 요청"),
	CONFLICT(HttpStatus.CONFLICT, "COMMON_409", "충돌"),
	FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON_403", "금지된 요청"),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_500", "서버 에러, 관리자에게 문의하세요"), // 일반적인 에러
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON_405", "허용되지 않은 메소드"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON_404", "찾을 수 없음"),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON_401", "권한 없음");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

}
