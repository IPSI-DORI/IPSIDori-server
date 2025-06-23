package com.server.dori.domain.curriculum.presentation.dto.response;

public record AICourseResponse(
	String courseId,
	String title,
	String description,
	String subject,
	String teacher,
	String grade,
	String platform,
	int price,
	String recommend
) {}