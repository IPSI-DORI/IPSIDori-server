package com.server.dori.domain.grade.presentation.dto.request;

public record GradeRequest(
	String subjects,

	String elective,

	String exam,

	int grade,

	int score,

	double percent
) {
}
