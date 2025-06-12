package com.server.dori.domain.member.entity.sub;

import com.server.dori.domain.member.exception.MemberInvalidException;

import lombok.Getter;

public enum LearningStyle {
	CONCEPTUAL("개념 학습 유형", 1, 4, new String[] {
		"글로 정리하거나 요약하면서 이해해요",
		"설명을 듣거나 말로 설명하면서 익혀요",
		"직접 써보거나 문제를 풀면서 체득해요",
		"영상이나 그림 등 시각자료를 통해 이해해요"
	}),
	PROBLEM_SOLVING("문제풀이 유형", 5, 8, new String[] {
		"개념부터 다시 정리해보는 편이에요",
		"선생님이나 친구에게 바로 물어봐요",
		"비슷한 문제를 찾아보면서 해결해요",
		"답 먼저 보고 이해하려고 해요"
	}),
	PREPARATION("준비성 유형", 9, 12, new String[] {
		"이미 계획대로 다 끝나 있어요",
		"중요한 부분만 골라서 복습해요",
		"벼락치기로 몰아쳐요",
		"마음만 불안하고 결국은 딴짓해요"
	}),
	MOTIVATION("동기부여 유형", 13, 16, new String[] {
		"목표를 세우고 스스로 채찍질해요",
		"누군가 응원하거나 인정해줄 때 힘나요",
		"결과보다 배움 자체가 즐거워요",
		"노력한 만큼 좋은 성적이 나오는게 좋아요"
	});

	@Getter
	private final String description;
	private final int minScore;
	private final int maxScore;
	@Getter
	private final String[] questions;

	LearningStyle(String description, int minScore, int maxScore, String[] questions) {
		this.description = description;
		this.minScore = minScore;
		this.maxScore = maxScore;
		this.questions = questions;
	}

	public static LearningStyle fromScore(int score) {
		for (LearningStyle style : values()) {
			if (score >= style.minScore && score <= style.maxScore) {
				return style;
			}
		}
		throw MemberInvalidException.invalidLearningStyleScore();
	}
}
