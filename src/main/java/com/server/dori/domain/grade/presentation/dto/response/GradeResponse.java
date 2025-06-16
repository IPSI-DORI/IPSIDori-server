package com.server.dori.domain.grade.presentation.dto.response;

import java.time.LocalDate;

import com.server.dori.domain.curriculum.entity.Curriculum;
import com.server.dori.domain.grade.entity.Grade;

public record GradeResponse(
	Long curriculum,
	String subjects,
	String elective,
	String exam,
	String score,
	String grade,
	String percent,
	LocalDate createdAt
) {
	public static GradeResponse from(Grade grade) {
		return new GradeResponse(
			grade.getCurriculum(),
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
