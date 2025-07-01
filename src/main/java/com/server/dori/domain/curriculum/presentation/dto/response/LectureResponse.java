package com.server.dori.domain.curriculum.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "개별 강의 응답 DTO")
public record LectureResponse(

	@Schema(description = "강의 제목", example = "도형의 성질")
	String title,

	@Schema(description = "강의 세부 정보", example = "핵심 개념 위주 설명")
	String info
) {}
