package com.server.dori.domain.test.presentation.dto;

import java.time.LocalDateTime;

import com.server.dori.domain.test.entity.Test;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TestResponseDto {
	private Long id;
	private String message;
	private LocalDateTime createdAt;

	public static TestResponseDto fromEntity(Test test) {
		return TestResponseDto.builder()
			.id(test.getId())
			.message(test.getMessage())
			.createdAt(test.getCreatedAt())
			.build();
	}
}