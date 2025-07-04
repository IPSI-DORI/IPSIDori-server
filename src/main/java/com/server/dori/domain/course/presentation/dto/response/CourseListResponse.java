package com.server.dori.domain.course.presentation.dto.response;

import com.server.dori.domain.course.entity.Course;

public record CourseListResponse(
	Long id,
	Long creator,
	String title,
	String subject,
	String grade,
	String platform,
	int price
) {
	public static CourseListResponse of(Course course) {
		return new CourseListResponse(
			course.getId(),
			course.getCreator().getId(),
			course.getTitle(),
			course.getSubject(),
			course.getGrade(),
			course.getPlatform(),
			course.getPrice()
		);
	}
}
