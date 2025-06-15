package com.server.dori.domain.member.service.implementation;

import org.springframework.stereotype.Component;

import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.MemberInfo;
import com.server.dori.domain.member.entity.sub.CharacterType;
import com.server.dori.domain.member.exception.MemberConflictException;
import com.server.dori.domain.member.exception.MemberInvalidException;
import com.server.dori.domain.member.presentation.dto.request.MemberSignupRequestDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberValidator {
	public void validateSignupRequest(Member member, MemberSignupRequestDto requestDto) {
		// 이미 가입이 완료된 회원인지 검증
		if (member.isMemberInfoCompleted()) {
			throw MemberConflictException.signupAlreadyCompleted();
		}

		// 재수/반수 학생 정보 검증
		if (!requestDto.isRetryStudentInfoValid()) {
			throw MemberInvalidException.missingRetryStudentInfo();
		}
	}

	public void validateMemberCompleted(Member member) {
		if (member.getMemberInfo() == null || !member.getMemberInfo().isMemberInfoCompleted()) {
			throw MemberInvalidException.signupNotCompleted();
		}
	}

	public void validateMemberInfo(MemberInfo memberInfo) {
		if (memberInfo == null) {
			throw MemberInvalidException.signupNotCompleted();
		}
	}

	public void validateLearningStyleScore(MemberInfo memberInfo) {
		if (!CharacterType.isValidScore(memberInfo.getLearningStyleScore())) {
			throw MemberInvalidException.invalidCharacterScore();
		}
	}
}
