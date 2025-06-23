package com.server.dori.grade.entity;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.presentation.dto.request.GradeRequest;

class GradeTest {

	@DisplayName("성적이 정상적으로 저장된다.")
	@Test
	void testGradeBuilder() {
		// given
		Long curriculumId = 1L;
		String subjects = "국어";
		String elective = "언어와 매체";
		String exam = "6월 평가원 모의고사";
		int score = 95;
		int grade = 1;
		double percent = 90.2;
		LocalDate createdAt = LocalDate.now();

		// when
		Grade gradeEntity = Grade.builder()
			.curriculum(curriculumId)
			.subjects(subjects)
			.elective(elective)
			.exam(exam)
			.score(score)
			.grade(grade)
			.percent(percent)
			.createdAt(createdAt)
			.build();

		// then
		assertThat(gradeEntity.getCurriculum()).isEqualTo(curriculumId);
		assertThat(gradeEntity.getSubjects()).isEqualTo(subjects);
		assertThat(gradeEntity.getElective()).isEqualTo(elective);
		assertThat(gradeEntity.getExam()).isEqualTo(exam);
		assertThat(gradeEntity.getScore()).isEqualTo(score);
		assertThat(gradeEntity.getGrade()).isEqualTo(grade);
		assertThat(gradeEntity.getPercent()).isEqualTo(percent);
		assertThat(gradeEntity.getCreatedAt()).isNotNull();
	}

	@DisplayName("성적을 정상적으로 수정한다.")
	@Test
	void testSaveGrade() {
		// given
		Grade gradeEntity = Grade.builder()
			.curriculum(1L)
			.subjects("국어")
			.elective("언어와 매체")
			.exam("6월 평가원 모의고사")
			.score(95)
			.grade(1)
			.percent(90.2)
			.createdAt(LocalDate.now())
			.build();

		GradeRequest request = new GradeRequest(1L, "6월 평가원 모의고사", 95, 1, 90.2);

		// when
		gradeEntity.saveGrade(request);

		// then
		assertThat(gradeEntity.getExam()).isEqualTo(request.exam());
		assertThat(gradeEntity.getScore()).isEqualTo(request.score());
		assertThat(gradeEntity.getGrade()).isEqualTo(request.grade());
		assertThat(gradeEntity.getPercent()).isEqualTo(request.percent());
	}
}