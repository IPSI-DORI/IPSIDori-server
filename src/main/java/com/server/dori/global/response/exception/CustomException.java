package com.server.dori.global.response.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

	public CustomException(HttpStatus httpStatus, String code, String message) {
		super(message);
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}
}
