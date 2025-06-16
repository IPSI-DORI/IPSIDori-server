package com.server.dori.domain.test.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.test.entity.Test;
import com.server.dori.domain.test.entity.repository.TestRepository;
import com.server.dori.domain.test.exception.TestBadRequestException;
import com.server.dori.domain.test.exception.TestNotFoundException;
import com.server.dori.domain.test.presentation.dto.TestRequest;
import com.server.dori.domain.test.presentation.dto.TestResponse;
import com.server.dori.domain.test.service.TestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestServiceImpl implements TestService {
	private final TestRepository testRepository;

	@Override
	@Transactional
	public TestResponse createTest(TestRequest request) {
		Test test = Test.builder()
			.message(request.message())
			.build();

		return TestResponse.fromEntity(testRepository.save(test));
	}

	@Override
	public TestResponse getTest(Long id) {
		return TestResponse.fromEntity(testRepository.findById(id)
			.orElseThrow(() -> TestNotFoundException.testNotFound()));
	}

	@Override
	public void throwCustomException() {
		throw TestBadRequestException.testBadRequest();
	}

	@Override
	public void throwRuntimeException() {
		throw new RuntimeException("런타임 예외처리");
	}
}
