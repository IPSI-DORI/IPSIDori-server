package com.server.dori.member.entity.sub;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.server.dori.domain.member.entity.sub.SocialType;

class SocialTypeTest {

	@DisplayName("모든 SocialType enum이 정의되어 있는지 확인한다.")
	@Test
	void testAllSocialTypeValues() {
		// given
		SocialType[] expectedValues = {SocialType.KAKAO, SocialType.NONE};

		// when
		SocialType[] actualValues = SocialType.values();

		// then
		assertThat(actualValues).hasSize(2);
		assertThat(actualValues).containsExactly(expectedValues);
	}

	@DisplayName("SocialType enum의 이름이 올바른지 확인한다.")
	@Test
	void testSocialTypeNames() {
		// given & when & then
		assertThat(SocialType.KAKAO.name()).isEqualTo("KAKAO");
		assertThat(SocialType.NONE.name()).isEqualTo("NONE");
	}
}
