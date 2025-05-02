package com.server.dori.global.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonSuccessStatus implements SuccessStatus {
	OK(HttpStatus.OK, "COMMON_200", "성공"),
	CREATED(HttpStatus.CREATED, "COMMON_201", "생성됨"),
	NO_CONTENT(HttpStatus.NO_CONTENT, "COMMON_204", "콘텐츠 없음");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

}
