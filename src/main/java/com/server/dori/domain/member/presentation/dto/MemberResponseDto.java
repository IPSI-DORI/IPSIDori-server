package com.server.dori.domain.member.presentation.dto;

import com.server.dori.domain.member.entity.Grade;
import com.server.dori.domain.member.entity.LearningStyle;
import com.server.dori.domain.member.entity.Member;

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
	Integer learningStyleScore
) {
	public static MemberResponseDto from(Member member) {
		return new MemberResponseDto(
			member.getId(),
			member.getEmail(),
			member.getNickname(),
			member.getProfile().getGrade(),
			member.getProfile().getCurrentUniversity(),
			member.getProfile().getCurrentMajor(),
			member.getProfile().getTargetUniversity(),
			member.getProfile().getTargetMajor(),
			member.getProfile().getLearningStyle(),
			member.getProfile().getLearningStyleScore()
		);
	}
}
