package com.server.dori.global.response.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponseDto {
	private final boolean isSuccess;
	private final String code;
	private final String message;
	private final Object errors;

	public static ApiErrorResponseDto of(ErrorStatus errorStatus) {
		return ApiErrorResponseDto.builder()
			.isSuccess(false)
			.code(errorStatus.getCode())
			.message(errorStatus.getMessage())
			.build();
	}

	public static ApiErrorResponseDto of(ErrorStatus errorStatus, Object errors) {
		return ApiErrorResponseDto.builder()
		.isSuccess(false)
		.code(errorStatus.getCode())
		.message(errorStatus.getMessage())
		.errors(errors)
		.build();
	}
}
