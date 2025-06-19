package com.server.dori.domain.curriculum.presentation.dto.request;

public record AICourseResponse(
	String courseId,
	String title,
	String description,
	String subject,
	String teacher,
	String grade,
	String platform,
	boolean isPaid,
	int price,
	String recommend
) {}