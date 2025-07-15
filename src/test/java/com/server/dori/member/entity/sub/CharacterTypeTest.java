package com.server.dori.member.entity.sub;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.server.dori.domain.member.entity.sub.CharacterType;
import com.server.dori.domain.member.exception.MemberInvalidException;

class CharacterTypeTest {

	@DisplayName("점수에 따라 올바른 캐릭터 타입이 반환된다.")
	@Test
	void testFromScore_ValidScores() {
		// given & when & then
		assertThat(CharacterType.fromScore(4)).isEqualTo(CharacterType.NORI);
		assertThat(CharacterType.fromScore(6)).isEqualTo(CharacterType.NORI);

		assertThat(CharacterType.fromScore(7)).isEqualTo(CharacterType.DORI);
		assertThat(CharacterType.fromScore(9)).isEqualTo(CharacterType.DORI);

		assertThat(CharacterType.fromScore(10)).isEqualTo(CharacterType.MORI);
		assertThat(CharacterType.fromScore(12)).isEqualTo(CharacterType.MORI);

		assertThat(CharacterType.fromScore(13)).isEqualTo(CharacterType.LAGI);
		assertThat(CharacterType.fromScore(16)).isEqualTo(CharacterType.LAGI);
	}

	@DisplayName("유효하지 않은 점수로 캐릭터 타입을 조회하면 예외가 발생한다.")
	@Test
	void testFromScore_InvalidScore() {
		// given
		int invalidScore = 3;

		// when & then
		assertThatThrownBy(() -> CharacterType.fromScore(invalidScore))
			.isInstanceOf(MemberInvalidException.class)
			.hasMessage("캐릭터 점수가 유효하지 않습니다. 4점 이상 16점 이하의 값이어야 합니다.");
	}

	@DisplayName("범위를 벗어난 높은 점수로 캐릭터 타입을 조회하면 예외가 발생한다.")
	@Test
	void testFromScore_TooHighScore() {
		// given
		int tooHighScore = 17;

		// when & then
		assertThatThrownBy(() -> CharacterType.fromScore(tooHighScore))
			.isInstanceOf(MemberInvalidException.class)
			.hasMessage("캐릭터 점수가 유효하지 않습니다. 4점 이상 16점 이하의 값이어야 합니다.");
	}

	@DisplayName("점수 유효성 검증이 올바르게 동작한다.")
	@Test
	void testIsValidScore() {
		// given & when & then
		assertThat(CharacterType.isValidScore(3)).isFalse();
		assertThat(CharacterType.isValidScore(4)).isTrue();
		assertThat(CharacterType.isValidScore(10)).isTrue();
		assertThat(CharacterType.isValidScore(16)).isTrue();
		assertThat(CharacterType.isValidScore(17)).isFalse();
	}

	@DisplayName("캐릭터별 이름과 설명이 올바른지 확인한다.")
	@Test
	void testCharacterNameAndDescription() {
		// given & when & then
		assertThat(CharacterType.NORI.getName()).isEqualTo("노리");
		assertThat(CharacterType.NORI.getDescription()).isEqualTo("기록형");

		assertThat(CharacterType.DORI.getName()).isEqualTo("도리");
		assertThat(CharacterType.DORI.getDescription()).isEqualTo("인강형");

		assertThat(CharacterType.MORI.getName()).isEqualTo("모리");
		assertThat(CharacterType.MORI.getDescription()).isEqualTo("암기형");

		assertThat(CharacterType.LAGI.getName()).isEqualTo("라기");
		assertThat(CharacterType.LAGI.getDescription()).isEqualTo("벼락치기형");
	}

	@DisplayName("캐릭터별 특성이 올바른지 확인한다.")
	@Test
	void testCharacterCharacteristics() {
		// given & when & then
		assertThat(CharacterType.NORI.getCharacteristics()).hasSize(3);
		assertThat(CharacterType.NORI.getCharacteristics().get(0))
			.isEqualTo("기록이 곧 기억! 정리하고 쓰면서 공부하는 타입이에요.");

		assertThat(CharacterType.DORI.getCharacteristics()).hasSize(3);
		assertThat(CharacterType.DORI.getCharacteristics().get(0))
			.isEqualTo("인강으로 배우는 게 제일 잘 맞는 타입이에요.");

		assertThat(CharacterType.MORI.getCharacteristics()).hasSize(3);
		assertThat(CharacterType.MORI.getCharacteristics().get(0))
			.isEqualTo("노트 필기나 암기 카드가 공부의 필수템이에요.");

		assertThat(CharacterType.LAGI.getCharacteristics()).hasSize(3);
		assertThat(CharacterType.LAGI.getCharacteristics().get(0))
			.isEqualTo("벼락치기의 천재, 단기간 집중력이 남다른 타입이에요.");
	}

	@DisplayName("이미지 URL이 올바르게 생성된다.")
	@Test
	void testGetImageUrl() {
		// given
		String baseUrl = "https://example.com/images";

		// when & then
		assertThat(CharacterType.NORI.getImageUrl(baseUrl))
			.isEqualTo("https://example.com/images/nori/노리-1.png");

		assertThat(CharacterType.DORI.getImageUrl(baseUrl))
			.isEqualTo("https://example.com/images/dori/도리-1.png");

		assertThat(CharacterType.MORI.getImageUrl(baseUrl))
			.isEqualTo("https://example.com/images/mori/모리-1.png");

		assertThat(CharacterType.LAGI.getImageUrl(baseUrl))
			.isEqualTo("https://example.com/images/ragi/라기-1.png");
	}

	@DisplayName("모든 캐릭터 타입 enum이 정의되어 있는지 확인한다.")
	@Test
	void testAllCharacterTypeValues() {
		// given
		CharacterType[] expectedValues = {
			CharacterType.NORI, CharacterType.DORI,
			CharacterType.MORI, CharacterType.LAGI
		};

		// when
		CharacterType[] actualValues = CharacterType.values();

		// then
		assertThat(actualValues).hasSize(4);
		assertThat(actualValues).containsExactly(expectedValues);
	}
}
