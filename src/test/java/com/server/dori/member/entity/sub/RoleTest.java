package com.server.dori.member.entity.sub;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.server.dori.domain.member.entity.sub.Role;

class RoleTest {

	@DisplayName("모든 Role enum이 정의되어 있는지 확인한다.")
	@Test
	void testAllRoleValues() {
		// given
		Role[] expectedValues = {Role.USER, Role.ADMIN};

		// when
		Role[] actualValues = Role.values();

		// then
		assertThat(actualValues).hasSize(2);
		assertThat(actualValues).containsExactly(expectedValues);
	}

	@DisplayName("Role enum의 이름이 올바른지 확인한다.")
	@Test
	void testRoleNames() {
		// given & when & then
		assertThat(Role.USER.name()).isEqualTo("USER");
		assertThat(Role.ADMIN.name()).isEqualTo("ADMIN");
	}
}
