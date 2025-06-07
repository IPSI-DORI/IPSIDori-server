package com.server.dori.domain.member.presentation.dto;

import com.server.dori.domain.member.entity.Grade;

public record ProfileUpdateDto(
	Grade grade,
	String currentUniversity,
	String currentMajor,
	String targetUniversity,
	String targetMajor,
	Integer learningStyleScore
) {
}
