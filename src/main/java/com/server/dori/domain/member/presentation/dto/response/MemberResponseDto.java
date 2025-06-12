package com.server.dori.domain.member.presentation.dto;

import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.sub.Grade;
import com.server.dori.domain.member.entity.sub.LearningStyle;

public record MemberResponseDto(
	Long id,
	String email,
	String nickname,
	Grade grade,
	String currentUniversity,
	String currentMajor,
	String targetUniversity,
	String targetMajor,
	LearningStyle learningStyle,
	int learningStyleScore
) {
	public static MemberResponseDto from(Member member) {
		return new MemberResponseDto(
			member.getId(),
			member.getEmail(),
			member.getNickname(),
			member.getMemberInfo().getGrade(),
			member.getMemberInfo().getCurrentUniversity(),
			member.getMemberInfo().getCurrentMajor(),
			member.getMemberInfo().getTargetUniversity(),
			member.getMemberInfo().getTargetMajor(),
			member.getMemberInfo().getLearningStyle(),
			member.getMemberInfo().getLearningStyleScore()
		);
	}
}
