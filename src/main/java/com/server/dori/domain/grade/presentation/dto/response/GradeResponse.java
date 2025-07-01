package com.server.dori.domain.grade.presentation.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.member.entity.Member;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "성적 응답 DTO")
public record GradeResponse(

	@Schema(description = "과목명", example = "수학")
	String subjects,

	@Schema(description = "선택 과목", example = "기하")
	String elective,

	@Schema(description = "시험 종류", example = "6월 평가원 모의고사"
		+ "")
	String exam,

	@Schema(description = "점수", example = "92")
	int score,

	@Schema(description = "등급", example = "1")
	int grade,

	@Schema(description = "백분위", example = "96.5")
	double percent,

	@Schema(description = "성적 기록 날짜", example = "2025-06-25")
	LocalDate createdAt,

	@Schema(description = "성적 생성자", example = "1")
	Member creator
) {
	public static GradeResponse from(Grade grade) {
		return new GradeResponse(
			grade.getSubjects(),
			grade.getElective(),
			grade.getExam(),
			grade.getScore(),
			grade.getGrade(),
			grade.getPercent(),
			grade.getCreatedAt(),
			grade.getCreator()
		);
	}

	public static List<GradeResponse> from(List<Grade> grades) {
		return grades.stream()
			.map(GradeResponse::from)
			.collect(Collectors.toList());
	}
}
