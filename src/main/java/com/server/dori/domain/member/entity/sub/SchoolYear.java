package com.server.dori.domain.member.entity.sub;

import lombok.Getter;

@Getter
public enum SchoolYear {
	HIGH_1("고등학교 1학년", false),
	HIGH_2("고등학교 2학년", false),
	HIGH_3("고등학교 3학년", false),
	JAESU("재수", true),
	BANSU("반수", true);

	private final String description;
	private final boolean isRetry;

	SchoolYear(String description, boolean isRetry) {
		this.description = description;
		this.isRetry = isRetry;
	}
}
