package com.server.dori.domain.grade.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Schema(description = "성적 정보 요청 DTO")
public record GradeRequest(

	@Schema(description = "과목명", example = "수학")
	String subjects,

	@Schema(description = "선택 과목", example = "확률과 통계")
	String elective,

	@Schema(description = "응시한 시험", example = "6월 모의고사")
	String exam,

	@Schema(description = "등급", example = "2")
	@Min(1) @Max(9)
	int grade,

	@Schema(description = "시험 점수", example = "85")
	@Min(0) @Max(100)
	int score,

	@Schema(description = "백분위", example = "12.3")
	@DecimalMin(value = "0.1", inclusive = true)
	@DecimalMax(value = "100.0", inclusive = true)
	double percent

) {
}