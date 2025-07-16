package com.server.dori.domain.member.presentation.dto.request;

import com.server.dori.domain.member.entity.sub.SchoolYear;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "회원 정보 수정 요청 DTO")
public record MemberInfoUpdate(
	@Schema(description = "사용자 닉네임", example = "홍길동")
	@NotBlank String nickname,

	@Schema(description = "학년", example = "HIGH_1/HIGH_2/HIGH_3/JAESU/BANSU",
		allowableValues = {"HIGH_1", "HIGH_2", "HIGH_3", "JAESU", "BANSU"})
	@NotNull SchoolYear schoolYear,

	@Schema(description = "희망 대학교", example = "서울대학교")
	@NotBlank String targetUniversity,

	@Schema(description = "희망 전공", example = "컴퓨터공학과")
	@NotBlank String targetMajor
) {
}
