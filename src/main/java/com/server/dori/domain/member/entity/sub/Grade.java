package com.server.dori.domain.member.entity.sub;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.server.dori.domain.member.exception.MemberInvalidException;

import lombok.Getter;

@Getter
public enum Grade {
	HIGH_1("고등학교 1학년", false),
	HIGH_2("고등학교 2학년", false),
	HIGH_3("고등학교 3학년", false),
	RETRY_1("재수", true),
	RETRY_2("반수", true);

	private final String description;
	private final boolean isRetry;

	Grade(String description, boolean isRetry) {
		this.description = description;
		this.isRetry = isRetry;
	}

	@JsonCreator
	public static Grade from(String value) {
		if (value == null) {
			throw MemberInvalidException.invalidGradeValue();
		}
		return Stream.of(values())
			.filter(grade -> grade.name().equalsIgnoreCase(value))
			.findFirst()
			.orElseThrow(MemberInvalidException::invalidGradeValue);
	}
}
