package com.server.dori.domain.test.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dori.domain.test.presentation.dto.TestRequest;
import com.server.dori.domain.test.presentation.dto.TestResponse;
import com.server.dori.domain.test.service.TestService;
import com.server.dori.global.response.CustomApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {
	private final TestService testService;

	@PostMapping
	public ResponseEntity<CustomApiResponse<TestResponse>> createTest(@Valid @RequestBody TestRequest request) {
		return CustomApiResponse.created(testService.createTest(request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomApiResponse<TestResponse>> getTest(@PathVariable Long id) {
		return CustomApiResponse.ok(testService.getTest(id));
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
