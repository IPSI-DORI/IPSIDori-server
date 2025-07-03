package com.server.dori.domain.course.presentation.dto.request;

import java.util.List;

public record CourseRequest(
	CourseDto recommendedCourse,
	List<LectureDto> lectureList
) {
	public record CourseDto(
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

	public record LectureDto(
		String title,
		String info
	) {}
}
