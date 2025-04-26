package com.server.dori.global.response.exception;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	private ResponseEntity<ApiErrorResponseDto> createErrorResponse(ErrorStatus errorStatus, String errorMessage) {
		log.error(errorMessage);
		return ResponseEntity
			.status(errorStatus.getHttpStatus())
			.body(ApiErrorResponseDto.of(errorStatus));
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ApiErrorResponseDto> handleCustomException(CustomException customException) {
		ErrorStatus errorStatus = customException.getErrorStatus();
		return createErrorResponse(
			errorStatus,
			String.format("커스텀 에러: %s, 에러 코드 : %s", errorStatus.getMessage(), errorStatus.getCode())
		);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponseDto> handleValidationException(
		MethodArgumentNotValidException methodArgumentNotValidException) {

		Map<String, String> errors = new HashMap<>();
		methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(error ->
			errors.put(error.getField(), error.getDefaultMessage()));

		return ResponseEntity
			.status(CommonErrorStatus.BAD_REQUEST.getHttpStatus())
			.body(ApiErrorResponseDto.of(CommonErrorStatus.BAD_REQUEST, errors));
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiErrorResponseDto> handleAccessDeniedException(
		AccessDeniedException accessDeniedException) {
		return createErrorResponse(
			CommonErrorStatus.FORBIDDEN,
			"접근 권한 오류 : " + accessDeniedException.getMessage()
		);
	}

	@ExceptionHandler({NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
	public ResponseEntity<ApiErrorResponseDto> handleNoHandlerFoundException(Exception exception) {
		return createErrorResponse(
			CommonErrorStatus.NOT_FOUND,
			"존재하지 않는 API : " + exception.getMessage()
		);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponseDto> handleAllException(Exception exception) {
		return createErrorResponse(
			CommonErrorStatus.INTERNAL_SERVER_ERROR,
			"알지 못한 에러: " + exception.getMessage()
		);
	}
}
