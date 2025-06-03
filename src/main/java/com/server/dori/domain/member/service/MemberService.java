package com.server.dori.domain.member.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.presentation.dto.MemberSignupRequestDto;

public interface MemberService extends UserDetailsService {
	Member getMemberById(Long memberId);

	Member findMemberByEmail(String email);

	boolean existsByEmail(String email);

	Member signupWithAdditionalInfo(MemberSignupRequestDto requestDto);
}
