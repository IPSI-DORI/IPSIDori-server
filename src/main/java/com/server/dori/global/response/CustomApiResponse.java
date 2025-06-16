package com.server.dori.global.response;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"isSuccess", "code", "message", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CustomApiResponse<T>(
	boolean isSuccess,
	String code,
	String message,
	T data
) {

	private static <T> CustomApiResponse<T> of(SuccessStatus status, T data) {
		return new CustomApiResponse<>(
			true,
			status.getCode(),
			status.getMessage(),
			data
		);
	}

	private static <T> ResponseEntity<CustomApiResponse<T>> buildResponse(SuccessStatus status, T data) {
		return ResponseEntity
			.status(status.getHttpStatus())
			.body(of(status, data));
	}

	// (200) -> 조회
	public static <T> ResponseEntity<CustomApiResponse<T>> ok(T data) {
		return buildResponse(CommonSuccessStatus.OK, data);
	}

	// (200) -> 조회
	public static ResponseEntity<CustomApiResponse<Void>> ok() {
		return ok(null);
	}

	// (201) -> 생성
	public static <T> ResponseEntity<CustomApiResponse<T>> created(T data) {
		return buildResponse(CommonSuccessStatus.CREATED, data);
	}

	// (201) -> 생성
	public static ResponseEntity<CustomApiResponse<Void>> created() {
		return created(null);
	}

	// (204) -> 삭제, 수정
	public static ResponseEntity<CustomApiResponse<Void>> noContent() {
		return buildResponse(CommonSuccessStatus.NO_CONTENT, null);
	}
}
