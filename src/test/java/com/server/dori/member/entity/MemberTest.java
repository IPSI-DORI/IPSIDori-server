package com.server.dori.member.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.sub.Role;
import com.server.dori.domain.member.entity.sub.SocialType;

class MemberTest {

	@DisplayName("회원이 정상적으로 생성된다.")
	@Test
	void testMemberBuilder() {
		// given
		String email = "test@example.com";
		Role role = Role.USER;
		SocialType socialType = SocialType.KAKAO;

		// when
		Member member = Member.builder()
			.email(email)
			.role(role)
			.socialType(socialType)
			.build();

		// then
		assertThat(member.getEmail()).isEqualTo(email);
		assertThat(member.getRole()).isEqualTo(role);
		assertThat(member.getSocialType()).isEqualTo(socialType);
		assertThat(member.getMemberInfo()).isNull();
	}

	@DisplayName("MemberInfo를 정상적으로 초기화한다.")
	@Test
	void testInitializeMemberInfo() {
		// given
		Member member = Member.builder()
			.email("test@example.com")
			.role(Role.USER)
			.socialType(SocialType.KAKAO)
			.build();

		// when
		member.initializeMemberInfo();

		// then
		assertThat(member.getMemberInfo()).isNotNull();
		assertThat(member.getMemberInfo().getMember()).isEqualTo(member);
	}

	@DisplayName("회원 정보가 완료되지 않은 상태를 확인한다.")
	@Test
	void testIsMemberInfoCompleted_NotCompleted() {
		// given
		Member member = Member.builder()
			.email("test@example.com")
			.role(Role.USER)
			.socialType(SocialType.KAKAO)
			.build();

		// when & then
		assertThat(member.isMemberInfoCompleted()).isFalse();
	}

	@DisplayName("MemberInfo가 초기화되었지만 정보가 완료되지 않은 상태를 확인한다.")
	@Test
	void testIsMemberInfoCompleted_InitializedButNotCompleted() {
		// given
		Member member = Member.builder()
			.email("test@example.com")
			.role(Role.USER)
			.socialType(SocialType.KAKAO)
			.build();

		// when
		member.initializeMemberInfo();

		// then
		assertThat(member.isMemberInfoCompleted()).isFalse();
	}
}
