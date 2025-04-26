package com.server.dori.global.response.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
	private final ErrorStatus errorStatus;

	public CustomException(ErrorStatus errorStatus) {
		super(errorStatus.getMessage());
		this.errorStatus = errorStatus;
	}
}
