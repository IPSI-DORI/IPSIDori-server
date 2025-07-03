package com.server.dori.domain.course.presentation.dto.response;

import java.util.List;

import com.server.dori.domain.course.entity.Course;

public record CourseResponse(
	Long id,
	Long creator,
	CourseDto recommendedCourse,
	List<LectureDto> lectureList
) {
	public static CourseResponse of(Course course) {
		CourseDto courseDto = new CourseDto(
			course.getTitle(),
			course.getDescription(),
			course.getSubject(),
			course.getTeacher(),
			course.getGrade(),
			course.getPlatform(),
			course.getPrice(),
			course.getRecommend()
		);

		List<LectureDto> lectures = course.getLectureList().stream()
			.map(lecture -> new LectureDto(
				lecture.getTitle(),
				lecture.getInfo()
			))
			.toList();

		return new CourseResponse(
			course.getId(),
			course.getCreator().getId(),
			courseDto,
			lectures
		);
	}

	public record CourseDto(
		String title,
		String description,
		String subject,
		String teacher,
		String grade,
		String platform,
		int price,
		String recommend
	) {}

	public record LectureDto(
		String title,
		String info
	) {}
}
