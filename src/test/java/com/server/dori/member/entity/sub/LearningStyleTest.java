package com.server.dori.member.entity.sub;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.server.dori.domain.member.entity.sub.LearningStyle;
import com.server.dori.domain.member.exception.MemberInvalidException;

class LearningStyleTest {

	@DisplayName("점수에 따라 올바른 학습 스타일이 반환된다.")
	@Test
	void testFromScore_ValidScores() {
		// given & when & then
		assertThat(LearningStyle.fromScore(1)).isEqualTo(LearningStyle.CONCEPTUAL);
		assertThat(LearningStyle.fromScore(4)).isEqualTo(LearningStyle.CONCEPTUAL);

		assertThat(LearningStyle.fromScore(5)).isEqualTo(LearningStyle.PROBLEM_SOLVING);
		assertThat(LearningStyle.fromScore(8)).isEqualTo(LearningStyle.PROBLEM_SOLVING);

		assertThat(LearningStyle.fromScore(9)).isEqualTo(LearningStyle.PREPARATION);
		assertThat(LearningStyle.fromScore(12)).isEqualTo(LearningStyle.PREPARATION);

		assertThat(LearningStyle.fromScore(13)).isEqualTo(LearningStyle.MOTIVATION);
		assertThat(LearningStyle.fromScore(16)).isEqualTo(LearningStyle.MOTIVATION);
	}

	@DisplayName("유효하지 않은 점수로 학습 스타일을 조회하면 예외가 발생한다.")
	@Test
	void testFromScore_InvalidScore() {
		// given
		int invalidScore = 0;

		// when & then
		assertThatThrownBy(() -> LearningStyle.fromScore(invalidScore))
			.isInstanceOf(MemberInvalidException.class)
			.hasMessage("유효하지 않은 학습 스타일 점수입니다.");
	}

	@DisplayName("범위를 벗어난 높은 점수로 학습 스타일을 조회하면 예외가 발생한다.")
	@Test
	void testFromScore_TooHighScore() {
		// given
		int tooHighScore = 17;

		// when & then
		assertThatThrownBy(() -> LearningStyle.fromScore(tooHighScore))
			.isInstanceOf(MemberInvalidException.class)
			.hasMessage("유효하지 않은 학습 스타일 점수입니다.");
	}

	@DisplayName("학습 스타일별 설명이 올바른지 확인한다.")
	@Test
	void testLearningStyleDescriptions() {
		// given & when & then
		assertThat(LearningStyle.CONCEPTUAL.getDescription()).isEqualTo("개념 학습 유형");
		assertThat(LearningStyle.PROBLEM_SOLVING.getDescription()).isEqualTo("문제풀이 유형");
		assertThat(LearningStyle.PREPARATION.getDescription()).isEqualTo("준비성 유형");
		assertThat(LearningStyle.MOTIVATION.getDescription()).isEqualTo("동기부여 유형");
	}

	@DisplayName("학습 스타일별 질문이 올바른지 확인한다.")
	@Test
	void testLearningStyleQuestions() {
		// given & when & then
		assertThat(LearningStyle.CONCEPTUAL.getQuestions()).hasSize(4);
		assertThat(LearningStyle.CONCEPTUAL.getQuestions()[0]).isEqualTo("글로 정리하거나 요약하면서 이해해요");

		assertThat(LearningStyle.PROBLEM_SOLVING.getQuestions()).hasSize(4);
		assertThat(LearningStyle.PROBLEM_SOLVING.getQuestions()[0]).isEqualTo("개념부터 다시 정리해보는 편이에요");

		assertThat(LearningStyle.PREPARATION.getQuestions()).hasSize(4);
		assertThat(LearningStyle.PREPARATION.getQuestions()[0]).isEqualTo("이미 계획대로 다 끝나 있어요");

		assertThat(LearningStyle.MOTIVATION.getQuestions()).hasSize(4);
		assertThat(LearningStyle.MOTIVATION.getQuestions()[0]).isEqualTo("목표를 세우고 스스로 채찍질해요");
	}

	@DisplayName("모든 학습 스타일 enum이 정의되어 있는지 확인한다.")
	@Test
	void testAllLearningStyleValues() {
		// given
		LearningStyle[] expectedValues = {
			LearningStyle.CONCEPTUAL, LearningStyle.PROBLEM_SOLVING,
			LearningStyle.PREPARATION, LearningStyle.MOTIVATION
		};

		// when
		LearningStyle[] actualValues = LearningStyle.values();

		// then
		assertThat(actualValues).hasSize(4);
		assertThat(actualValues).containsExactly(expectedValues);
	}
}
