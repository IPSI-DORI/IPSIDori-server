package com.server.dori.domain.member.presentation.dto.request;

import com.server.dori.domain.member.entity.sub.Grade;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "회원 정보 수정 요청 DTO")
public record MemberInfoUpdateDto(
	@Schema(description = "사용자 닉네임", example = "홍길동")
	@NotBlank String nickname,

	@Schema(description = "학년<br> " + "HIGH_1: 고등학교 1학년<br> " + "HIGH_2: 고등학교 2학년<br> " + "HIGH_3: 고등학교 3학년<br> "
		+ "RETRY_1: 재수<br> " + "RETRY_2: 반수",
		example = "HIGH_1",
		allowableValues = {"HIGH_1", "HIGH_2", "HIGH_3", "RETRY_1", "RETRY_2"})
	@NotNull Grade grade,

	@Schema(description = "희망 대학교", example = "서울대학교")
	@NotBlank String targetUniversity,

	@Schema(description = "희망 전공", example = "컴퓨터공학과")
	@NotBlank String targetMajor
) {
} 