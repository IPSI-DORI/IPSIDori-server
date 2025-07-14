package com.server.dori.member.entity;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import com.server.dori.domain.member.entity.CustomUserDetails;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.sub.Role;
import com.server.dori.domain.member.entity.sub.SocialType;

class CustomUserDetailsTest {

	@DisplayName("Member로부터 CustomUserDetails가 정상적으로 생성된다.")
	@Test
	void testCustomUserDetailsCreation() throws NoSuchFieldException, IllegalAccessException {
		// given
		Member member = Member.builder()
			.email("test@example.com")
			.role(Role.USER)
			.socialType(SocialType.KAKAO)
			.build();

		// Member ID 설정 (리플렉션 사용)
		Field idField = Member.class.getDeclaredField("id");
		idField.setAccessible(true);
		idField.set(member, 1L);

		// when
		CustomUserDetails userDetails = new CustomUserDetails(member);

		// then
		assertThat(userDetails.getMemberId()).isEqualTo(1L);
		assertThat(userDetails.getEmail()).isEqualTo("test@example.com");
		assertThat(userDetails.getUsername()).isEqualTo("test@example.com");
	}

	@DisplayName("권한이 올바르게 설정된다.")
	@Test
	void testAuthorities() throws NoSuchFieldException, IllegalAccessException {
		// given
		Member adminMember = Member.builder()
			.email("admin@example.com")
			.role(Role.ADMIN)
			.socialType(SocialType.KAKAO)
			.build();

		Field idField = Member.class.getDeclaredField("id");
		idField.setAccessible(true);
		idField.set(adminMember, 2L);

		// when
		CustomUserDetails userDetails = new CustomUserDetails(adminMember);

		// then
		assertThat(userDetails.getAuthorities()).hasSize(1);
		GrantedAuthority authority = userDetails.getAuthorities().iterator().next();
		assertThat(authority.getAuthority()).isEqualTo("ROLE_ADMIN");
	}

	@DisplayName("소셜 로그인 사용으로 비밀번호는 null이다.")
	@Test
	void testPasswordIsNull() throws NoSuchFieldException, IllegalAccessException {
		// given
		Member member = Member.builder()
			.email("test@example.com")
			.role(Role.USER)
			.socialType(SocialType.KAKAO)
			.build();

		Field idField = Member.class.getDeclaredField("id");
		idField.setAccessible(true);
		idField.set(member, 1L);

		// when
		CustomUserDetails userDetails = new CustomUserDetails(member);

		// then
		assertThat(userDetails.getPassword()).isNull();
	}

	@DisplayName("계정 상태가 모두 활성화되어 있다.")
	@Test
	void testAccountStatus() throws NoSuchFieldException, IllegalAccessException {
		// given
		Member member = Member.builder()
			.email("test@example.com")
			.role(Role.USER)
			.socialType(SocialType.KAKAO)
			.build();

		Field idField = Member.class.getDeclaredField("id");
		idField.setAccessible(true);
		idField.set(member, 1L);

		// when
		CustomUserDetails userDetails = new CustomUserDetails(member);

		// then
		assertThat(userDetails.isAccountNonExpired()).isTrue();
		assertThat(userDetails.isAccountNonLocked()).isTrue();
		assertThat(userDetails.isCredentialsNonExpired()).isTrue();
		assertThat(userDetails.isEnabled()).isTrue();
	}

	@DisplayName("USER 권한이 올바르게 설정된다.")
	@Test
	void testUserRole() throws NoSuchFieldException, IllegalAccessException {
		// given
		Member userMember = Member.builder()
			.email("user@example.com")
			.role(Role.USER)
			.socialType(SocialType.KAKAO)
			.build();

		Field idField = Member.class.getDeclaredField("id");
		idField.setAccessible(true);
		idField.set(userMember, 3L);

		// when
		CustomUserDetails userDetails = new CustomUserDetails(userMember);

		// then
		assertThat(userDetails.getAuthorities()).hasSize(1);
		GrantedAuthority authority = userDetails.getAuthorities().iterator().next();
		assertThat(authority.getAuthority()).isEqualTo("ROLE_USER");
	}
}
