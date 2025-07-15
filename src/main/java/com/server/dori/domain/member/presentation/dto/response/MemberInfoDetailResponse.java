package com.server.dori.domain.member.presentation.dto.response;

import com.server.dori.domain.member.entity.sub.SchoolYear;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "회원 정보 상세 응답 DTO")
public record MemberInfoDetailResponse(
	@Schema(description = "회원 ID", example = "1")
	Long memberId,

	@Schema(description = "사용자 닉네임", example = "홍길동")
	String nickname,

	@Schema(description = "학년", example = "HIGH_1/HIGH_2/HIGH_3/JAESU/BANSU",
		allowableValues = {"HIGH_1", "HIGH_2", "HIGH_3", "JAESU", "BANSU"})
	SchoolYear schoolYear,

	@Schema(description = "희망 대학교", example = "서울대학교")
	String targetUniversity,

	@Schema(description = "희망 전공", example = "컴퓨터공학과")
	String targetMajor,

	@Schema(description = "캐릭터 이미지 URL", example = "https://xxx/xxx.png")
	String characterImageUrl
) {
}
