package com.server.dori.domain.curriculum.presentation.dto.response;

import java.time.LocalDateTime;

import com.server.dori.domain.curriculum.entity.Curriculum;
import com.server.dori.domain.curriculum.entity.type.Platform;

public record CurriculumSurveyResponse(
	String subject,
	String elective,
	String studyTime,
	String studyDays,
	String question1,
	String question2,
	Platform platform,
	LocalDateTime ceateAt
) {
	public static CurriculumSurveyResponse from(Curriculum curriculum) {
		return new CurriculumSurveyResponse(
			curriculum.getSubject(),
			curriculum.getElective(),
			curriculum.getStudyTime(),
			curriculum.getStudyDays(),
			curriculum.getQuestion1(),
			curriculum.getQuestion2(),
			curriculum.getPlatform(),
			curriculum.getCreatedAt()
		);
	}
}
