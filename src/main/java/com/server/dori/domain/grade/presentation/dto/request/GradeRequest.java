package com.server.dori.domain.grade.presentation.dto.request;

public record GradeRequest(
	Long gradeId,
	String exam,
	int score,
	int grade,
	double percent
) {
}
