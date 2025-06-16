package com.server.dori.global.response;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"isSuccess", "code", "message", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CustomApiReponse<T>(
	boolean isSuccess,
	String code,
	String message,
	T data
) {

	private static <T> CustomApiReponse<T> of(SuccessStatus status, T data) {
		return new CustomApiReponse<>(
			true,
			status.getCode(),
			status.getMessage(),
			data
		);
	}

	private static <T> ResponseEntity<CustomApiReponse<T>> buildResponse(SuccessStatus status, T data) {
		return ResponseEntity
			.status(status.getHttpStatus())
			.body(of(status, data));
	}

	// (200) -> 조회
	public static <T> ResponseEntity<CustomApiReponse<T>> ok(T data) {
		return buildResponse(CommonSuccessStatus.OK, data);
	}

	// (200) -> 조회
	public static ResponseEntity<CustomApiReponse<Void>> ok() {
		return ok(null);
	}

	// (201) -> 생성
	public static <T> ResponseEntity<CustomApiReponse<T>> created(T data) {
		return buildResponse(CommonSuccessStatus.CREATED, data);
	}

	// (201) -> 생성
	public static ResponseEntity<CustomApiReponse<Void>> created() {
		return created(null);
	}

	// (204) -> 삭제, 수정
	public static ResponseEntity<CustomApiReponse<Void>> noContent() {
		return buildResponse(CommonSuccessStatus.NO_CONTENT, null);
	}
}
