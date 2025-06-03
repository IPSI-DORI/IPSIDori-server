package com.server.dori.domain.member.presentation.dto;

import com.server.dori.domain.member.entity.Grade;
import com.server.dori.domain.member.entity.LearningStyle;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.Subject;

public record MemberResponseDto(
	Long id,
	String email,
	String nickname,
	Grade grade,
	String currentUniversity,
	String currentMajor,
	Subject koreanSubject,
	Subject mathSubject,
	Subject inquirySubject1,
	Subject inquirySubject2,
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
			member.getGrade(),
			member.getCurrentUniversity(),
			member.getCurrentMajor(),
			member.getKoreanSubject(),
			member.getMathSubject(),
			member.getInquirySubject1(),
			member.getInquirySubject2(),
			member.getTargetUniversity(),
			member.getTargetMajor(),
			member.getLearningStyle(),
			member.getLearningStyleScore()
		);
	}
}
