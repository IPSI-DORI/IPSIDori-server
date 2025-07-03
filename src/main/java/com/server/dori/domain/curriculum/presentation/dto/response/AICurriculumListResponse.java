package com.server.dori.domain.curriculum.presentation.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "AI 커리큘럼 추천 리스트 응답 DTO")
public record AICurriculumListResponse(
	@Schema(description = "AI가 추천한 커리큘럼 리스트 (예: 3개)")
	List<AICurriculumResponse> curriculumList
) {}