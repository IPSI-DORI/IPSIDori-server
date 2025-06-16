package com.server.dori.domain.member.entity.sub;

import java.util.Arrays;
import java.util.List;

import com.server.dori.domain.member.exception.MemberInvalidException;

import lombok.Getter;

@Getter
public enum CharacterType {
	NORI("노리", "기록형", 4, 6, "nori", "노리", Arrays.asList(
		"기록이 곧 기억! 정리하고 쓰면서 공부하는 타입이에요.",
		"공부 내용을 스스로 정리하며 머릿속에 체계화해요.",
		"손으로 쓰거나 정리할 때 더 잘 이해하고 기억해요."
	)),
	DORI("도리", "인강형", 7, 9, "dori", "도리", Arrays.asList(
		"인강으로 배우는 게 제일 잘 맞는 타입이에요.",
		"강의 흐름을 따라가며 이해하고, 필요한 부분은 여러 번 돌려봐요.",
		"듣고, 정리하고, 다시 듣기! 체계적인 복습 루틴이 있어요."
	)),
	MORI("모리", "암기형", 10, 12, "mori", "모리", Arrays.asList(
		"노트 필기나 암기 카드가 공부의 필수템이에요.",
		"외워야 할 것과 버릴 것을 명확하게 구분해요.",
		"'자주 보기'와 '반복하기'로 완벽하게 내 것으로 만들어요."
	)),
	LAGI("라기", "벼락치기형", 13, 16, "ragi", "라기", Arrays.asList(
		"벼락치기의 천재, 단기간 집중력이 남다른 타입이에요.",
		"마감 직전의 압박감 속에서 실력을 발휘해요.",
		"'몰입할 때 제대로 몰입'하는 순간 집중형 스타일이에요."
	));

	private final String name;
	private final String description;
	private final int minScore;
	private final int maxScore;
	private final String folderName;
	private final String characterName;
	private final List<String> characteristics;

	CharacterType(String name, String description, int minScore, int maxScore, String folderName, String characterName,
		List<String> characteristics) {
		this.name = name;
		this.description = description;
		this.minScore = minScore;
		this.maxScore = maxScore;
		this.folderName = folderName;
		this.characterName = characterName;
		this.characteristics = characteristics;
	}

	public static CharacterType fromScore(int learningStyleScore) {
		return Arrays.stream(values())
			.filter(type -> learningStyleScore >= type.minScore && learningStyleScore <= type.maxScore)
			.findFirst()
			.orElseThrow(MemberInvalidException::invalidCharacterScore);
	}

	public String getImageUrl(String baseUrl) {
		return String.format("%s/%s/%s-1.png", baseUrl, this.folderName, this.characterName);
	}

	public static boolean isValidScore(int score) {
		return Arrays.stream(values())
			.anyMatch(type -> score >= type.minScore && score <= type.maxScore);
	}
}
