package com.server.dori.domain.test.presentation.dto;

import jakarta.validation.constraints.NotNull;

public record TestRequestDto(
	@NotNull String message
) {
}
