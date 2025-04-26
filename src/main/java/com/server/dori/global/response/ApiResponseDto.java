package com.server.dori.global.response;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"isSuccess", "code", "message", "data"})
public class ApiResponseDto<T> {
	private final boolean isSuccess;
	private final String code;
	private final String message;
	@JsonInclude(JsonInclude.Include.NON_NULL) // null 명시 필요하면 어노테이션 제거
	private final T data;

	@Builder
	private ApiResponseDto(boolean isSuccess, String code, String message, T data) {
		this.isSuccess = isSuccess;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	private static <T> ApiResponseDto<T> of(SuccessStatus status, T data) {
		return ApiResponseDto.<T>builder()
			.isSuccess(true)
			.code(status.getCode())
			.message(status.getMessage())
			.data(data)
			.build();
	}

	private static <T> ResponseEntity<ApiResponseDto<T>> buildResponse(SuccessStatus status, T data) {
		return ResponseEntity
			.status(status.getHttpStatus())
			.body(of(status, data));
	}

	// (200) -> 조회
	public static <T> ResponseEntity<ApiResponseDto<T>> ok(T data) {
		return buildResponse(CommonSuccessStatus.OK, data);
	}

	// (200) -> 조회
	public static ResponseEntity<ApiResponseDto<Void>> ok() {
		return ok(null);
	}

	// (201) -> 생성
	public static <T> ResponseEntity<ApiResponseDto<T>> created(T data) {
		return buildResponse(CommonSuccessStatus.CREATED, data);
	}

	// (201) -> 생성
	public static ResponseEntity<ApiResponseDto<Void>> created() {
		return created(null);
	}

	// (204) -> 삭제, 수정
	public static ResponseEntity<ApiResponseDto<Void>> noContent() {
		return buildResponse(CommonSuccessStatus.NO_CONTENT, null);
	}
}
