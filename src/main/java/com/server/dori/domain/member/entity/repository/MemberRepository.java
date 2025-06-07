package com.server.dori.domain.member.entity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.SocialType;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(String email);

	boolean existsByEmail(String email);

	Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}
