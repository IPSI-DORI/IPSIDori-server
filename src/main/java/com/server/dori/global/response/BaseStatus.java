package com.server.dori.global.response;

import org.springframework.http.HttpStatus;

public interface BaseStatus {
	String getCode();

	String getMessage();

	HttpStatus getHttpStatus(); // ResponseEntity 의 HttpStatus
}
