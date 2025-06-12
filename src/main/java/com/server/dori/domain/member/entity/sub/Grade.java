package com.server.dori.domain.member.entity.sub;

import lombok.Getter;

@Getter
public enum Grade {
	HIGH_1("고등학교 1학년"),
	HIGH_2("고등학교 2학년"),
	HIGH_3("고등학교 3학년"),
	RETRY_1("재수"),
	RETRY_2("반수");

	private final String description;

	Grade(String description) {
		this.description = description;
	}
}
