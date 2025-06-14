package com.server.dori.domain.member.presentation.dto.response;

import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.sub.Grade;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "회원 정보 상세 응답 DTO")
public record MemberInfoResponseDto(
	@Schema(description = "회원 ID", example = "1")
	Long memberId,

	@Schema(description = "사용자 닉네임", example = "홍길동")
	String nickname,

	@Schema(description = "학년<br> " + "HIGH_1: 고등학교 1학년<br> " + "HIGH_2: 고등학교 2학년<br> " + "HIGH_3: 고등학교 3학년<br> "
		+ "RETRY_1: 재수<br> " + "RETRY_2: 반수",
		example = "HIGH_1",
		allowableValues = {"HIGH_1", "HIGH_2", "HIGH_3", "RETRY_1", "RETRY_2"})
	Grade grade,

	@Schema(description = "희망 대학교", example = "서울대학교")
	String targetUniversity,

	@Schema(description = "희망 전공", example = "컴퓨터공학과")
	String targetMajor
) {
	public static MemberInfoResponseDto from(Member member) {
		String nickname = (member.getMemberInfo() != null && member.getMemberInfo().getNickname() != null)
			? member.getMemberInfo().getNickname()
			: member.getNickname();

		return new MemberInfoResponseDto(
			member.getId(),
			nickname,
			member.getMemberInfo() != null ? member.getMemberInfo().getGrade() : null,
			member.getMemberInfo() != null ? member.getMemberInfo().getTargetUniversity() : null,
			member.getMemberInfo() != null ? member.getMemberInfo().getTargetMajor() : null
		);
	}
} 