package com.server.dori.domain.test.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class TestNotFoundException extends CustomException {

	public TestNotFoundException(HttpStatus httpStatus, String code, String message) {
		super(httpStatus, code, message);
	}

	public static TestNotFoundException testNotFound() {
		return new TestNotFoundException(HttpStatus.NOT_FOUND, "TEST_404_1", "테스트 데이터 탐색 불가");
	}
} 