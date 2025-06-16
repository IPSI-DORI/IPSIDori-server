package com.server.dori.domain.test.presentation.dto;

import java.time.LocalDateTime;

import com.server.dori.domain.test.entity.Test;

public record TestResponse(
	Long id,
	String message,
	LocalDateTime createdAt
) {

	public static TestResponse fromEntity(Test test) {
		return new TestResponse(
			test.getId(),
			test.getMessage(),
			test.getCreatedAt()
		);
	}
}
