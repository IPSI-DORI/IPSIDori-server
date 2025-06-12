package com.server.dori.domain.member.presentation.dto;

import com.server.dori.domain.member.entity.sub.Grade;

public record MemberInfoUpdateDto(
	Grade grade,
	String currentUniversity,
	String currentMajor,
	String targetUniversity,
	String targetMajor,
	int learningStyleScore
) {
}
