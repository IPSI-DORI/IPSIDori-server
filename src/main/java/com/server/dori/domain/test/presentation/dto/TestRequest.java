package com.server.dori.domain.test.presentation.dto;

import jakarta.validation.constraints.NotNull;

public record TestRequest(
	@NotNull String message
) {
}
