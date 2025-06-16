package com.server.dori.domain.test.service;

import com.server.dori.domain.test.presentation.dto.TestRequest;
import com.server.dori.domain.test.presentation.dto.TestResponse;

public interface TestService {
	TestResponse createTest(TestRequest request);

	TestResponse getTest(Long id);

	void throwCustomException();

	void throwRuntimeException();
}
