package com.server.dori.member.entity.sub;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.server.dori.domain.member.entity.sub.SchoolYear;

class SchoolYearTest {

	@DisplayName("고등학생 학년의 isRetry가 false인지 확인한다.")
	@Test
	void testHighSchoolYears_IsNotRetry() {
		// given & when & then
		assertThat(SchoolYear.HIGH_1.isRetry()).isFalse();
		assertThat(SchoolYear.HIGH_2.isRetry()).isFalse();
		assertThat(SchoolYear.HIGH_3.isRetry()).isFalse();
	}

	@DisplayName("재수생/반수생 학년의 isRetry가 true인지 확인한다.")
	@Test
	void testRetryStudentYears_IsRetry() {
		// given & when & then
		assertThat(SchoolYear.JAESU.isRetry()).isTrue();
		assertThat(SchoolYear.BANSU.isRetry()).isTrue();
	}

	@DisplayName("학년별 설명이 올바른지 확인한다.")
	@Test
	void testSchoolYearDescriptions() {
		// given & when & then
		assertThat(SchoolYear.HIGH_1.getDescription()).isEqualTo("고등학교 1학년");
		assertThat(SchoolYear.HIGH_2.getDescription()).isEqualTo("고등학교 2학년");
		assertThat(SchoolYear.HIGH_3.getDescription()).isEqualTo("고등학교 3학년");
		assertThat(SchoolYear.JAESU.getDescription()).isEqualTo("재수");
		assertThat(SchoolYear.BANSU.getDescription()).isEqualTo("반수");
	}

	@DisplayName("모든 학년 enum이 정의되어 있는지 확인한다.")
	@Test
	void testAllSchoolYearValues() {
		// given
		SchoolYear[] expectedValues = {
			SchoolYear.HIGH_1, SchoolYear.HIGH_2, SchoolYear.HIGH_3,
			SchoolYear.JAESU, SchoolYear.BANSU
		};

		// when
		SchoolYear[] actualValues = SchoolYear.values();

		// then
		assertThat(actualValues).hasSize(5);
		assertThat(actualValues).containsExactly(expectedValues);
	}
}
