package com.server.dori.domain.course.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "강의 수강 상태 업데이트 DTO")
public record LectureRequest(
	boolean isCheck
) {
}
