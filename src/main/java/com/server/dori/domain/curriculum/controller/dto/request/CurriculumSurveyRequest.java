package com.server.dori.domain.curriculum.controller.dto.request;

import com.server.dori.domain.curriculum.entity.type.Platform;

public record CurriculumSurveyRequest(
	String subject,
	String elective,
	String studyTime,
	String studyDays,
	String question1,
	String question2,
	Platform platform
) {
}
