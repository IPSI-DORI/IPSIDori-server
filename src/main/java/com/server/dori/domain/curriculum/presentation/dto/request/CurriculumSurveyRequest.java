package com.server.dori.domain.curriculum.presentation.dto.request;

import com.server.dori.domain.curriculum.entity.type.Platform;

public record CurriculumSurveyRequest(
	String subject,
	String elective,
	String studyTime,
	String studyDays,
	String weakProblemQuestion,
	String learningGoalQuestion,
	Platform platform
) {
}
