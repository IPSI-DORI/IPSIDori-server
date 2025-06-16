package com.server.dori.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.dori.domain.auth.exception.AuthNotFoundException;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.sub.SocialType;
import com.server.dori.domain.member.exception.MemberNotFoundException;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(String email);

	Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId);

	default Member getByEmail(String email) {
		return findByEmail(email)
			.orElseThrow(MemberNotFoundException::memberNotFoundException);
	}

	default Member getBySocialTypeAndSocialId(SocialType socialType, String socialId) {
		return findBySocialTypeAndSocialId(socialType, socialId)
			.orElseThrow(AuthNotFoundException::oauthUserNotFound);
	}

	default Member getById(Long memberId) {
		return findById(memberId)
			.orElseThrow(MemberNotFoundException::memberNotFoundException);
	}

	default Optional<Member> findOptionalBySocialTypeAndSocialId(SocialType socialType, String socialId) {
		return findBySocialTypeAndSocialId(socialType, socialId);
	}
}
