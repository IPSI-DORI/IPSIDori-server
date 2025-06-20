package com.server.dori.curriculum.entity;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.server.dori.domain.curriculum.entity.Curriculum;
import com.server.dori.domain.curriculum.entity.type.Platform;

class CurriculumTest {

	@DisplayName("Curriculum 빌더로 객체를 생성하면 값이 정상적으로 할당된다.")
	@Test
	void testCurriculumBuilder() {
		// given
		Long creator = 1L;
		String subject = "국어";
		String elective = "심화";
		String studyTime = "오후 2시";
		String studyDays = "월, 수, 금";
		String question1 = "어떤 부분이 어려우셨나요?";
		String question2 = "어떤 방식의 수업을 원하시나요?";
		Platform platform = Platform.EBSi;

		// when
		Curriculum curriculum = Curriculum.builder()
			.creator(creator)
			.subject(subject)
			.elective(elective)
			.studyTime(studyTime)
			.studyDays(studyDays)
			.question1(question1)
			.question2(question2)
			.createdAt(LocalDateTime.now())
			.platform(platform)
			.build();

		// then
		assertThat(curriculum.getCreator()).isEqualTo(creator);
		assertThat(curriculum.getSubject()).isEqualTo(subject);
		assertThat(curriculum.getElective()).isEqualTo(elective);
		assertThat(curriculum.getStudyTime()).isEqualTo(studyTime);
		assertThat(curriculum.getStudyDays()).isEqualTo(studyDays);
		assertThat(curriculum.getQuestion1()).isEqualTo(question1);
		assertThat(curriculum.getQuestion2()).isEqualTo(question2);
		assertThat(curriculum.getPlatform()).isEqualTo(platform);
		assertThat(curriculum.getCreatedAt()).isNotNull();
	}
}
