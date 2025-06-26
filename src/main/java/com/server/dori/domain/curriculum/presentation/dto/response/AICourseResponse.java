package com.server.dori.domain.curriculum.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "추천 강좌 정보 DTO")
public record AICourseResponse(

	@Schema(description = "강좌 Id", example = "S20240000904")
	String courseId,

	@Schema(description = "강좌 제목", example = "기하 완전 정복")
	String title,

	@Schema(description = "강좌 설명", example = "도형의 성질부터 활용까지 완벽하게 학습할 수 있는 강좌입니다.")
	String description,

	@Schema(description = "과목", example = "수학")
	String subject,

	@Schema(description = "강사 이름", example = "김수학 선생님")
	String teacher,

	@Schema(description = "추천 학년", example = "고2")
	String grade,

	@Schema(description = "강의 플랫폼", example = "메가스터디")
	String platform,

	@Schema(description = "가격 (단위: 원)", example = "100000")
	int price,

	@Schema(description = "추천 이유", example = "현재 수준과 목표에 가장 부합하는 강좌입니다.")
	String recommend
) {}
