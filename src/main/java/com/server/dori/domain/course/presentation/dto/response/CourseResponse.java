package com.server.dori.domain.course.presentation.dto.response;

import java.util.List;

import com.server.dori.domain.course.entity.Course;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "강의 상세 응답 DTO")
public record CourseResponse(

	@Schema(description = "강의 Id", example = "1")
	Long id,

	@Schema(description = "생성자 회원 Id", example = "10")
	Long creator,

	@Schema(description = "추천 강의 정보")
	CourseDto recommendedCourse,

	@Schema(description = "강의 목록")
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

	@Schema(description = "추천 강의 DTO")
	public record CourseDto(

		@Schema(description = "강의 제목", example = "문학/비문학 종합 강의")
		String title,

		@Schema(description = "강의 설명", example = "문학과 비문학을 아우르는 종합 강의입니다.")
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
