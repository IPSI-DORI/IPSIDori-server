package com.server.dori.domain.test.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.ErrorStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TestErrorStatus implements ErrorStatus {

	TEST_BAD_REQUEST(HttpStatus.BAD_REQUEST, "TEST_400_1", "테스트 요청 실패"),
	TEST_INVALID_INPUT(HttpStatus.BAD_REQUEST, "TEST_400_2", "테스트 입력 오류"),
	TEST_NOT_FOUND(HttpStatus.NOT_FOUND, "TEST_404_1", "테스트 데이터 탐색 불가"),
	TEST_INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "TEST_500_1", "테스트 서버 오류");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
