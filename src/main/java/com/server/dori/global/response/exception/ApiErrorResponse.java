package com.server.dori.global.response.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiErrorResponse(
	boolean isSuccess,
	String code,
	String message,
	Object errors
) {
}
