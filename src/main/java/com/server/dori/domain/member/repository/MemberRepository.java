package com.server.dori.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.dori.domain.auth.exception.AuthNotFoundException;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.sub.SocialType;
import com.server.dori.domain.member.exception.MemberNotFoundException;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(String email);

	Optional<Member> findBySocialType(SocialType socialType);

	default Member getByEmail(String email) {
		return findByEmail(email)
			.orElseThrow(() -> new MemberNotFoundException());
	}

	default Member getBySocialType(SocialType socialType) {
		return findBySocialType(socialType)
			.orElseThrow(AuthNotFoundException::oauthUserNotFound);
	}

	default Member getById(Long memberId) {
		return findById(memberId)
			.orElseThrow(() -> new MemberNotFoundException());
	}

	default Optional<Member> findOptionalBySocialType(SocialType socialType) {
		return findBySocialType(socialType);
	}
}
