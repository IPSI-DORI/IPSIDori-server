package com.server.dori.domain.course.presentation.dto.response;

import com.server.dori.domain.course.entity.Course;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "강의 리스트 응답 DTO")
public record CourseListResponse(

	@Schema(description = "강의 Id", example = "1")
	Long id,

	@Schema(description = "학생 Id", example = "10")
	Long creator,

	@Schema(description = "강의 제목", example = "문학/비문학 종합 강의")
	String title,

	@Schema(description = "과목", example = "국어")
	String subject,

	@Schema(description = "대상 학년", example = "고1")
	String grade,

	@Schema(description = "플랫폼", example = "ebsi")
	String platform,

	@Schema(description = "가격", example = "0")
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
