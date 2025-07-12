package com.server.dori.domain.course.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "강의 등록 요청 DTO")
public record CourseRequest(

	@Schema(description = "추천 강의 정보")
	CourseDto recommendedCourse,

	@Schema(description = "강의 상세 목록")
	List<LectureDto> lectureList

) {
	@Schema(description = "추천 강의 DTO")
	public record CourseDto(

		@Schema(description = "강의 Id", example = "1")
		String courseId,

		@Schema(description = "강의 제목", example = "[2025 수능완성] 문학/비문학 - 홍길동전 (실전편)")
		String title,

		@Schema(description = "강의 설명", example = "문학·비문학 개념 완성 강의")
		String description,

		@Schema(description = "과목", example = "국어")
		String subject,

		@Schema(description = "강사명", example = "홍길동")
		String teacher,

		@Schema(description = "대상 학년", example = "고1")
		String grade,

		@Schema(description = "플랫폼", example = "ebsi")
		String platform,

		@Schema(description = "가격", example = "0")
		int price,

		@Schema(description = "추천 대상 설명", example = "문학에 약한 학생에게 추천")
		String recommend
	) {}

	@Schema(description = "강의(lecture) DTO")
	public record LectureDto(

		@Schema(description = "강의 제목", example = "1강 OT")
		String title,

		@Schema(description = "강의 정보", example = "수업 내용에 대한 오리엔테이션입니다.")
		String info
	) {}
}
