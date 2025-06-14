package com.server.dori.domain.grade.presentation.dto.response;

import java.time.LocalDate;

import com.server.dori.domain.grade.entity.Grade;

public record GradeResponse(
	String subjects,
	String elective,
	String exam,
	String score,
	String grade,
	String percent,
	LocalDate createdAt) {
	public static GradeResponse from(Grade grade) {
		return new GradeResponse(
			grade.getSubjects(),
			grade.getElective(),
			grade.getExam(),
			grade.getScore(),
			grade.getGrade(),
			grade.getPercent(),
			grade.getCreatedAt()
		);
	}
}
