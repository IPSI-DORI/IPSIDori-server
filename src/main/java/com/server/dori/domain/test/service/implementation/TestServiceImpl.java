package com.server.dori.domain.test.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.test.entity.Test;
import com.server.dori.domain.test.entity.repository.TestRepository;
import com.server.dori.domain.test.exception.TestErrorStatus;
import com.server.dori.domain.test.exception.TestException;
import com.server.dori.domain.test.presentation.dto.TestRequestDto;
import com.server.dori.domain.test.presentation.dto.TestResponseDto;
import com.server.dori.domain.test.service.TestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestServiceImpl implements TestService {
	private final TestRepository testRepository;

	@Override
	@Transactional
	public TestResponseDto createTest(TestRequestDto request) {
		Test test = Test.builder()
			.message(request.message())
			.build();

		return TestResponseDto.fromEntity(testRepository.save(test));
	}

	@Override
	public TestResponseDto getTest(Long id) {
		return TestResponseDto.fromEntity(testRepository.findById(id)
			.orElseThrow(() -> new TestException(TestErrorStatus.TEST_NOT_FOUND)));
	}

	@Override
	public void throwCustomException() {
		throw new TestException(TestErrorStatus.TEST_BAD_REQUEST);
	}

	@Override
	public void throwRuntimeException() {
		throw new RuntimeException("런타임 예외처리");
	}
}
