package com.server.dori.domain.curriculum.presentation.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "AI 커리큘럼 추천 응답 DTO")
public record AICurriculumResponse(

	@Schema(description = "추천 강의")
	AICourseResponse recommendedCourse,

	@Schema(description = "추천 강의 리스트")
	List<LectureResponse> lectureList
) {}
