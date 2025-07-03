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
		String subjects = "국어";
		String elective = "언어와 매체";
		String exam = "6월 평가원 모의고사";
		int score = 95;
		int grade = 1;
		double percent = 90.2;
		LocalDate createdAt = LocalDate.now();

		// when
		Grade gradeEntity = Grade.builder()
			.subjects(subjects)
			.elective(elective)
			.exam(exam)
			.score(score)
			.grade(grade)
			.percent(percent)
			.createdAt(createdAt)
			.build();

		// then
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
	void testUpdateGrade() {
		// given
		Grade gradeEntity = Grade.builder()
			.subjects("국어")
			.elective("언어와 매체")
			.exam("6월 평가원 모의고사")
			.score(95)
			.grade(1)
			.percent(90.2)
			.createdAt(LocalDate.now())
			.build();

		GradeRequest updateRequest = new GradeRequest("수학", "기하", "9월 평가원 모의고사", 89, 2, 83.1);

		// when
		gradeEntity.updateGrade(updateRequest);

		// then
		assertThat(gradeEntity.getSubjects()).isEqualTo(updateRequest.subjects());
		assertThat(gradeEntity.getElective()).isEqualTo(updateRequest.elective());
		assertThat(gradeEntity.getExam()).isEqualTo(updateRequest.exam());
		assertThat(gradeEntity.getScore()).isEqualTo(updateRequest.score());
		assertThat(gradeEntity.getGrade()).isEqualTo(updateRequest.grade());
		assertThat(gradeEntity.getPercent()).isEqualTo(updateRequest.percent());
	}
}