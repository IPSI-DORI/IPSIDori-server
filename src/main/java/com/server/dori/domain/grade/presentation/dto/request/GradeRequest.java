package com.server.dori.domain.grade.presentation.dto.request;

public record GradeRequest(
	String exam,
	String score,
	String grade,
	String percent
) {
}
