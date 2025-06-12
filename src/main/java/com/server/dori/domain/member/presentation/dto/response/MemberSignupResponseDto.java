package com.server.dori.domain.member.presentation.dto.response;

import com.server.dori.domain.member.entity.Member;

public record MemberSignupResponseDto(
	Long memberId,
	String email,
	String nickname,
	boolean isMemberInfoCompleted
) {
	public static MemberSignupResponseDto from(Member member) {
		String nickname = (member.getMemberInfo() != null && member.getMemberInfo().getNickname() != null)
			? member.getMemberInfo().getNickname()
			: member.getNickname();

		return new MemberSignupResponseDto(
			member.getId(),
			member.getEmail(),
			nickname,
			member.isMemberInfoCompleted()
		);
	}
}
