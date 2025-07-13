package com.server.dori.domain.member.presentation.dto.request;

import com.server.dori.domain.member.entity.sub.SchoolYear;
import com.server.dori.domain.member.exception.MemberInvalidException;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "재수생/반수생 회원가입 요청 DTO")
public record RetryStudentSignupRequest(
	@Schema(description = "닉네임", example = "김재수")
	@NotBlank String nickname,

	@Schema(description = "학년", example = "JAESU/BANSU",
		allowableValues = {"JAESU", "BANSU"})
	@NotNull SchoolYear schoolYear,

	@Schema(description = "현재 대학교 (없는 경우 '해당없음')", example = "연세대학교")
	@NotBlank String currentUniversity,

	@Schema(description = "현재 전공 (없는 경우 '해당없음')", example = "컴퓨터공학과")
	@NotBlank String currentMajor,

	@Schema(description = "목표 대학교", example = "서울대학교")
	@NotBlank String targetUniversity,

	@Schema(description = "목표 전공", example = "컴퓨터공학과")
	@NotBlank String targetMajor,

	@Schema(description = "개념 학습 유형 답변 (1-4)", example = "1")
	@Min(1) @Max(4) int conceptualAnswer,

	@Schema(description = "문제풀이 유형 답변 (1-4)", example = "2")
	@Min(1) @Max(4) int problemSolvingAnswer,

	@Schema(description = "준비성 유형 답변 (1-4)", example = "3")
	@Min(1) @Max(4) int preparationAnswer,

	@Schema(description = "동기부여 유형 답변 (1-4)", example = "4")
	@Min(1) @Max(4) int motivationAnswer
) implements SignupRequest {

	public RetryStudentSignupRequest {
		// 재수생/반수생 학년 검증
		if (!schoolYear.isRetry()) {
			throw MemberInvalidException.invalidRetryStudentYear();
		}
	}

	@Override
	@Schema(description = "현재 대학교", example = "연세대학교")
	public String getCurrentUniversity() {
		return currentUniversity;
	}

	@Override
	@Schema(description = "현재 전공", example = "경영학과")
	public String getCurrentMajor() {
		return currentMajor;
	}
}
