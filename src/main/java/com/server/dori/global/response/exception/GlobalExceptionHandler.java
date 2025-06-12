package com.server.dori.global.response.exception;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ApiErrorResponseDto> handleCustomException(CustomException customException) {
		log.error("커스텀 에러: {}, 에러 코드 : {}", customException.getMessage(), customException.getCode());
		return ResponseEntity
			.status(customException.getHttpStatus())
			.body(new ApiErrorResponseDto(
				false,
				customException.getCode(),
				customException.getMessage(),
				null
			));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiErrorResponseDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		Throwable rootCause = e.getRootCause();
		if (rootCause instanceof CustomException customException) {
			log.error("잘못된 Enum 값 입력 (JsonCreator) 또는 기타 역직렬화 오류: {}", customException.getMessage());
			return ResponseEntity
				.status(customException.getHttpStatus())
				.body(new ApiErrorResponseDto(
					false,
					customException.getCode(),
					customException.getMessage(),
					null
				));
		}

		log.error("잘못된 요청 형식 : {}", e.getMessage());
		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(new ApiErrorResponseDto(
				false,
				"COMMON_400_2",
				"잘못된 형식의 요청입니다.",
				null
			));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponseDto> handleValidationException(
		MethodArgumentNotValidException methodArgumentNotValidException) {

		Map<String, String> errors = new HashMap<>();
		methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(error ->
			errors.put(error.getField(), error.getDefaultMessage()));

		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(new ApiErrorResponseDto(
				false,
				"COMMON_400",
				"잘못된 요청",
				errors
			));
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiErrorResponseDto> handleAccessDeniedException(
		AccessDeniedException accessDeniedException) {
		log.error("접근 권한 오류 : {}", accessDeniedException.getMessage());
		return ResponseEntity
			.status(HttpStatus.FORBIDDEN)
			.body(new ApiErrorResponseDto(
				false,
				"COMMON_403",
				"금지된 요청",
				null
			));
	}

	@ExceptionHandler({NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
	public ResponseEntity<ApiErrorResponseDto> handleNoHandlerFoundException(Exception exception) {
		log.error("존재하지 않는 API : {}", exception.getMessage());
		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(new ApiErrorResponseDto(
				false,
				"COMMON_404",
				"찾을 수 없음",
				null
			));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponseDto> handleAllException(Exception exception) {
		log.error("알지 못한 에러: {}", exception.getMessage());
		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(new ApiErrorResponseDto(
				false,
				"COMMON_500",
				"서버 에러, 관리자에게 문의하세요",
				null
			));
	}
}