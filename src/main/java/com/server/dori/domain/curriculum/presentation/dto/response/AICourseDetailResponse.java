package com.server.dori.domain.curriculum.presentation.dto.response;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(description = "AI 커리큘럼 상세 추천 응답 DTO")
public record AICourseDetailResponse(

		@Schema(description = "강좌 Id", example = "S20240000783")
		String course_id,

		@Schema(description = "강좌 제목", example = "[2026 수능개념] 주석쌤의 프러포즈 확률과 통계")
		String title,

		@Schema(description = "강좌 설명", example = "확률과 통계의 기본 개념을 명확히 전달하는 강좌")
		String description,

		@Schema(description = "과목", example = "확률과통계")
		String subject,

		@Schema(description = "추천 학년", example = "고3")
		String grade,

		@Schema(description = "강사 이름", example = "심주석")
		String teacher,

		@Schema(description = "강의 플랫폼", example = "ebsi")
		String platform,

		@Schema(description = "유료 여부", example = "false")
		boolean is_paid,

		@Schema(description = "가격 (단위: 원)", example = "0")
		int price,

		@Schema(description = "추천 이유", example = "문제의 체계적인 접근법을 배울 수 있습니다.")
		String recommend,

		@Schema(description = "강좌 URL", example = "https://www.ebsi.co.kr/ebs/lms/lmsx/retrieveSbjtDtl.ebs?courseId=S20240000783")
		String url,

		@Schema(description = "강의 리스트")
		List<LectureResponse> lectures_list
) {}