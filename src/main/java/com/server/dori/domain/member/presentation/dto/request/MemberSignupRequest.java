package com.server.dori.domain.member.presentation.dto.request;

import com.server.dori.domain.member.entity.sub.Grade;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "회원가입 요청 DTO (토큰에서 사용자 정보 자동 추출)")
public record MemberSignupRequest(
	@Schema(description = "닉네임", example = "홍길동")
	@NotBlank String nickname,

	@Schema(description = "학년<br> " + "HIGH_1: 고등학교 1학년<br> " + "HIGH_2: 고등학교 2학년<br> " + "HIGH_3: 고등학교 3학년<br> "
		+ "RETRY_1: 재수<br> " + "RETRY_2: 반수",
		example = "HIGH_1",
		allowableValues = {"HIGH_1", "HIGH_2", "HIGH_3", "RETRY_1", "RETRY_2"})
	@NotNull Grade grade,

	@Schema(description = "현재 대학교 (재수/반수인 경우 필수)", example = "서울대학교")
	String currentUniversity,

	@Schema(description = "현재 전공 (재수/반수인 경우 필수)", example = "컴퓨터공학과")
	String currentMajor,

	@Schema(description = "목표 대학교", example = "서울대학교")
	@NotBlank String targetUniversity,

	@Schema(description = "목표 전공", example = "컴퓨터공학과")
	@NotBlank String targetMajor,

	@Schema(description = "개념 학습 유형 답변 (1-4)", example = "1")
	@NotNull @Min(1) @Max(4) int conceptualAnswer,

	@Schema(description = "문제풀이 유형 답변 (1-4)", example = "2")
	@NotNull @Min(1) @Max(4) int problemSolvingAnswer,

	@Schema(description = "준비성 유형 답변 (1-4)", example = "3")
	@NotNull @Min(1) @Max(4) int preparationAnswer,

	@Schema(description = "동기부여 유형 답변 (1-4)", example = "4")
	@NotNull @Min(1) @Max(4) int motivationAnswer
) {
	public boolean isRetryStudent() {
		return grade != null && grade.isRetry();
	}

	public boolean isRetryStudentInfoValid() {
		if (!isRetryStudent()) {
			return true;
		}
		return currentUniversity != null && !currentUniversity.isBlank()
			&& currentMajor != null && !currentMajor.isBlank();
	}
}
