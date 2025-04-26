package com.server.dori.domain.test.service;

import com.server.dori.domain.test.presentation.dto.TestRequestDto;
import com.server.dori.domain.test.presentation.dto.TestResponseDto;

public interface TestService {
	TestResponseDto createTest(TestRequestDto request);

	TestResponseDto getTest(Long id);

	void throwCustomException();

	void throwRuntimeException();
}
