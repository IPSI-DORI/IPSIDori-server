package com.server.dori.curriculum.entity;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.server.dori.domain.curriculum.entity.Curriculum;
import com.server.dori.domain.curriculum.entity.type.Platform;

class CurriculumTest {

	@DisplayName("커리큘럼 설문이 정상적으로 저장된다.")
	@Test
	void testCurriculumBuilder() {
		// given
		Long creator = 1L;
		String subject = "국어";
		String elective = "언어와 매체";
		String studyTime = "2~3시간";
		String studyDays = "월, 수, 금";
		String weakProblemQuestion = "문제에서 요구하는 게 뭔지 잘 모르겠어요.";
		String learningGoalQuestion = "심화까지 깊이있게 배우기";
		Platform platform = Platform.EBSi;

		// when
		Curriculum curriculum = Curriculum.builder()
			.creator(creator)
			.subject(subject)
			.elective(elective)
			.studyTime(studyTime)
			.studyDays(studyDays)
			.weakProblemQuestion(weakProblemQuestion)
			.learningGoalQuestion(learningGoalQuestion)
			.createdAt(LocalDateTime.now())
			.platform(platform)
			.build();

		// then
		assertThat(curriculum.getCreator()).isEqualTo(creator);
		assertThat(curriculum.getSubject()).isEqualTo(subject);
		assertThat(curriculum.getElective()).isEqualTo(elective);
		assertThat(curriculum.getStudyTime()).isEqualTo(studyTime);
		assertThat(curriculum.getStudyDays()).isEqualTo(studyDays);
		assertThat(curriculum.getWeakProblemQuestion()).isEqualTo(weakProblemQuestion);
		assertThat(curriculum.getLearningGoalQuestion()).isEqualTo(learningGoalQuestion);
		assertThat(curriculum.getPlatform()).isEqualTo(platform);
		assertThat(curriculum.getCreatedAt()).isNotNull();
	}
}
