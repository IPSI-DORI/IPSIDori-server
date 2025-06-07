package com.server.dori.domain.member.presentation.dto;

import com.server.dori.domain.member.entity.Grade;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "회원가입 요청 DTO")
public record MemberSignupRequestDto(
	@Schema(description = "카카오 소셜 ID", example = "123456789")
	@NotBlank String socialId,

	@Schema(description = "이메일", example = "user@example.com")
	@Email @NotBlank String email,

	@Schema(description = "닉네임", example = "홍길동")
	@NotBlank String nickname,

	@Schema(description = "학년", example = "HIGH_1")
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
	@NotNull @Min(1) @Max(4) Integer conceptualAnswer,

	@Schema(description = "문제풀이 유형 답변 (1-4)", example = "2")
	@NotNull @Min(1) @Max(4) Integer problemSolvingAnswer,

	@Schema(description = "준비성 유형 답변 (1-4)", example = "3")
	@NotNull @Min(1) @Max(4) Integer preparationAnswer,

	@Schema(description = "동기부여 유형 답변 (1-4)", example = "4")
	@NotNull @Min(1) @Max(4) Integer motivationAnswer
) {
	public boolean isRetryStudent() {
		return grade == Grade.RETRY_1 || grade == Grade.RETRY_2;
	}

	public boolean isRetryStudentInfoValid() {
		if (!isRetryStudent()) {
			return true;
		}
		return currentUniversity != null && !currentUniversity.isBlank()
			   && currentMajor != null && !currentMajor.isBlank();
	}
}
