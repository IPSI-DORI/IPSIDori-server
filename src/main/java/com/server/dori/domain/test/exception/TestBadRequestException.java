package com.server.dori.domain.test.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class TestBadRequestException extends CustomException {

	public TestBadRequestException(HttpStatus httpStatus, String code, String message) {
		super(httpStatus, code, message);
	}

	public static TestBadRequestException testBadRequest() {
		return new TestBadRequestException(HttpStatus.BAD_REQUEST, "TEST_400_1", "테스트 요청 실패");
	}

	public static TestBadRequestException testInvalidInput() {
		return new TestBadRequestException(HttpStatus.BAD_REQUEST, "TEST_400_2", "테스트 입력 오류");
	}
} 