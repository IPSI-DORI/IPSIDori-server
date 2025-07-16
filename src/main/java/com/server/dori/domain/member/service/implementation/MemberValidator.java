package com.server.dori.domain.member.service.implementation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.exception.MemberConflictException;
import com.server.dori.domain.member.exception.MemberInvalidException;

import lombok.RequiredArgsConstructor;

/**
 * 회원 관련 비즈니스 로직 검증을 담당하는 클래스
 * 서비스 계층에서 사용되는 검증 로직만 포함
 */
@Service
@RequiredArgsConstructor
public class MemberValidator {

	/**
	 * 회원가입 요청 검증
	 * 이미 가입이 완료된 회원인지 확인
	 */
	public void validateSignupRequest(Member member) {
		if (member.isMemberInfoCompleted()) {
			throw MemberConflictException.signupAlreadyCompleted();
		}
	}

	/**
	 * 회원 정보 완료 여부 검증
	 * 회원가입이 완료되지 않은 회원의 정보 접근 시 사용
	 */
	public void validateMemberCompleted(Member member) {
		if (member.getMemberInfo() == null || !member.getMemberInfo().isMemberInfoCompleted()) {
			throw MemberInvalidException.signupNotCompleted();
		}
	}
}
