package com.server.dori.domain.member.presentation.dto.response;

import com.server.dori.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "마이페이지 메인 응답 DTO")
public record MemberSimpleResponseDto(
	@Schema(description = "회원 ID", example = "1")
	Long memberId,

	@Schema(description = "사용자 닉네임", example = "홍길동")
	String nickname
) {
	public static MemberSimpleResponseDto from(Member member) {
		String nickname = (member.getMemberInfo() != null && member.getMemberInfo().getNickname() != null)
			? member.getMemberInfo().getNickname()
			: member.getNickname();

		return new MemberSimpleResponseDto(
			member.getId(),
			nickname
		);
	}
} 