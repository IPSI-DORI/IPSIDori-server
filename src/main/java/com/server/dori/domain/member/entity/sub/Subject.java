package com.server.dori.domain.member.entity.sub;

import lombok.Getter;

@Getter
public enum Subject {
	// 국어
	KOREAN_READING("독서"),
	KOREAN_LITERATURE("문학"),
	KOREAN_LANGUAGE("언어와 매체"),
	KOREAN_SPEECH("화법과 작문"),

	// 수학
	MATH_1("수학 1"),
	MATH_2("수학 2"),
	PROBABILITY("확률과 통계"),
	CALCULUS("미적분"),
	GEOMETRY("기하"),

	// 사회탐구
	ETHICS_LIFE("생활과 윤리"),
	ETHICS_THOUGHT("윤리와 사상"),
	KOREAN_GEOGRAPHY("한국지리"),
	WORLD_GEOGRAPHY("세계지리"),
	EAST_ASIAN_HISTORY("동아시아사"),
	WORLD_HISTORY("세계사"),
	POLITICS("정치와 법"),
	ECONOMICS("경제"),
	SOCIETY_CULTURE("사회/문화"),

	// 과학탐구
	PHYSICS_1("물리학 1"),
	PHYSICS_2("물리학 2"),
	CHEMISTRY_1("화학 1"),
	CHEMISTRY_2("화학 2"),
	LIFE_SCIENCE_1("생명과학 1"),
	LIFE_SCIENCE_2("생명과학 2"),
	EARTH_SCIENCE_1("지구과학 1"),
	EARTH_SCIENCE_2("지구과학 2"),

	// 한국사
	KOREAN_HISTORY("한국사");

	private final String description;

	Subject(String description) {
		this.description = description;
	}
}
