package com.server.dori.global.response;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"isSuccess", "code", "message", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
	boolean isSuccess,
	String code,
	String message,
	T data
) {

	private static <T> ApiResponse<T> of(SuccessStatus status, T data) {
		return new ApiResponse<>(
			true,
			status.getCode(),
			status.getMessage(),
			data
		);
	}

	private static <T> ResponseEntity<ApiResponse<T>> buildResponse(SuccessStatus status, T data) {
		return ResponseEntity
			.status(status.getHttpStatus())
			.body(of(status, data));
	}

	// (200) -> 조회
	public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
		return buildResponse(CommonSuccessStatus.OK, data);
	}

	// (200) -> 조회
	public static ResponseEntity<ApiResponse<Void>> ok() {
		return ok(null);
	}

	// (201) -> 생성
	public static <T> ResponseEntity<ApiResponse<T>> created(T data) {
		return buildResponse(CommonSuccessStatus.CREATED, data);
	}

	// (201) -> 생성
	public static ResponseEntity<ApiResponse<Void>> created() {
		return created(null);
	}

	// (204) -> 삭제, 수정
	public static ResponseEntity<ApiResponse<Void>> noContent() {
		return buildResponse(CommonSuccessStatus.NO_CONTENT, null);
	}
}
