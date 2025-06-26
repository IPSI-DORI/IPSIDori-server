package com.server.dori.domain.grade.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "성적 입력 요청 DTO")
public record GradeWithCurriculumRequest(

	@Schema(description = "커리큘럼과 연결된 성적 ID", example = "1")
	@NotNull Long gradeId,

	@Schema(description = "시험 종류", example = "6월 평가원 모의고사")
	@NotBlank String exam,

	@Schema(description = "점수", example = "92")
	@Min(0) @Max(100) int score,

	@Schema(description = "등급", example = "1")
	@Min(1) @Max(9) int grade,

	@Schema(description = "백분위", example = "96.5")
	@DecimalMin("0.0") @DecimalMax("100.0") double percent
) {}
