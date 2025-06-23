package com.server.dori.domain.curriculum.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class ApiCallException extends CustomException {
	public ApiCallException(String message) {
		super(HttpStatus.GATEWAY_TIMEOUT, "Curriculum_504_1", message);
	}
}