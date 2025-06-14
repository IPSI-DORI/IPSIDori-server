package com.server.dori.domain.member.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "마이페이지 심플 정보 DTO")
public record MemberSimpleInfoResponseDto(
	@Schema(description = "회원 ID", example = "1")
	Long memberId,

	@Schema(description = "사용자 닉네임", example = "홍길동")
	String nickname,

	@Schema(description = "캐릭터 이미지 URL", example = "https://ipsi-dori-character.s3.ap-northeast-2.amazonaws.com/dori.png")
	String characterImageUrl
) {
}
