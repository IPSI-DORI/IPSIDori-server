package com.server.dori.domain.curriculum.presentation.dto.response;

import java.time.LocalDateTime;

import com.server.dori.domain.curriculum.entity.Curriculum;
import com.server.dori.domain.curriculum.entity.type.Platform;

public record CurriculumSurveyResponse(
	Long creator,
	String subject,
	String elective,
	String studyTime,
	String studyDays,
	String weakProblemQuestion,
	String learningGoalQuestion,
	Platform platform,
	LocalDateTime createAt,
	Long gradeId
) {
	public static CurriculumSurveyResponse of(Curriculum curriculum, Long gradeId) {
		return new CurriculumSurveyResponse(
			curriculum.getCreator(),
			curriculum.getSubject(),
			curriculum.getElective(),
			curriculum.getStudyTime(),
			curriculum.getStudyDays(),
			curriculum.getWeakProblemQuestion(),
			curriculum.getLearningGoalQuestion(),
			curriculum.getPlatform(),
			curriculum.getCreatedAt(),
			gradeId
		);
	}
}
