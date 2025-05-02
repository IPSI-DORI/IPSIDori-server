package com.server.dori.domain.test.presentation.dto;

import java.time.LocalDateTime;

import com.server.dori.domain.test.entity.Test;

public record TestResponseDto(
	Long id,
	String message,
	LocalDateTime createdAt
) {

	public static TestResponseDto fromEntity(Test test) {
		return new TestResponseDto(
			test.getId(),
			test.getMessage(),
			test.getCreatedAt()
		);
	}
}
