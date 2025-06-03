package com.server.dori.domain.member.presentation.dto;

import com.server.dori.domain.member.entity.Grade;
import com.server.dori.domain.member.entity.Subject;

public record ProfileUpdateDto(
	Grade grade,
	String currentUniversity,
	String currentMajor,
	Subject koreanSubject,
	Subject mathSubject,
	Subject inquirySubject1,
	Subject inquirySubject2,
	String targetUniversity,
	String targetMajor,
	Integer learningStyleScore
) {
}
