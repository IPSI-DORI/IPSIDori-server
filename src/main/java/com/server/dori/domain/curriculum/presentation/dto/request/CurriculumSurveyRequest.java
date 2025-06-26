package com.server.dori.domain.curriculum.presentation.dto.request;

import com.server.dori.domain.curriculum.entity.type.Platform;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "커리큘럼 설문 요청 DTO")
public record CurriculumSurveyRequest(

	@Schema(description = "공부 과목", example = "수학")
	@NotBlank String subject,

	@Schema(description = "선택 과목", example = "기하")
	@NotBlank String elective,

	@Schema(description = "하루 공부 시간", example = "1~2시간")
	@NotBlank String studyTime,

	@Schema(description = "주간 공부일수", example = "월, 화")
	@NotBlank String studyDays,

	@Schema(description = "문제를 풀 때 가장 고민되는 점은 무엇인가요?", example = "조건이 많거나 복잡한 문제에서 헷갈려요.")
	@NotBlank String weakProblemQuestion,

	@Schema(description = "현재 학습 목표가 무엇인가요?", example = "탄탄한 기본기 다지기")
	@NotBlank String learningGoalQuestion,

	@Schema(description = "강의 플랫폼", example = "EBSi",
		allowableValues = {"EBSi", "메가스터디", "대성마이맥", "이투스"})
	@NotNull Platform platform
) {}
