package com.server.dori.domain.test.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dori.domain.test.presentation.dto.TestRequestDto;
import com.server.dori.domain.test.presentation.dto.TestResponseDto;
import com.server.dori.domain.test.service.TestService;
import com.server.dori.global.response.ApiResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {
	private final TestService testService;

	@PostMapping
	public ResponseEntity<ApiResponseDto<TestResponseDto>> createTest(@Valid @RequestBody TestRequestDto request) {
		return ApiResponseDto.created(testService.createTest(request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDto<TestResponseDto>> getTest(@PathVariable Long id) {
		return ApiResponseDto.ok(testService.getTest(id));
	}

	@GetMapping("/exception/custom")
	public void throwCustomException() {
		testService.throwCustomException();
	}

	@GetMapping("/exception/runtime")
	public void throwRuntimeException() {
		testService.throwRuntimeException();
	}

	@GetMapping("/health")
	public ResponseEntity<String> healthCheck() {
		return ResponseEntity.ok("OK");
	}

}
