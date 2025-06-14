package com.server.dori.domain.test.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class TestInternalException extends CustomException {

	public TestInternalException(HttpStatus httpStatus, String code, String message) {
		super(httpStatus, code, message);
	}

	public static TestInternalException testInternalError() {
		return new TestInternalException(HttpStatus.INTERNAL_SERVER_ERROR, "TEST_500_1", "테스트 서버 오류");
	}
} 