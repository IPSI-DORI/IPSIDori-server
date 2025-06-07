package com.server.dori.domain.member.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.Role;
import com.server.dori.domain.member.entity.SocialType;
import com.server.dori.domain.member.entity.repository.MemberRepository;
import com.server.dori.domain.member.exception.MemberErrorStatus;
import com.server.dori.domain.member.exception.MemberException;
import com.server.dori.domain.member.presentation.dto.MemberSignupRequestDto;
import com.server.dori.domain.member.presentation.dto.ProfileUpdateDto;
import com.server.dori.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return memberRepository.findByEmail(username)
			.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
	}

	@Override
	@Transactional(readOnly = true)
	public Member getMemberById(Long memberId) {
		return memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberException(MemberErrorStatus.MEMBER_NOT_FOUND));
	}

	@Override
	@Transactional(readOnly = true)
	public Member findMemberByEmail(String email) {
		return memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberException(MemberErrorStatus.MEMBER_NOT_FOUND));
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsByEmail(String email) {
		return memberRepository.existsByEmail(email);
	}

	@Override
	@Transactional
	public Member signupWithAdditionalInfo(MemberSignupRequestDto requestDto) {
		// 이메일 중복 체크
		if (existsByEmail(requestDto.email())) {
			throw MemberException.duplicateEmail();
		}

		// 재수/반수 학생 정보 검증
		if (!requestDto.isRetryStudentInfoValid()) {
			throw MemberException.invalidGrade();
		}

		// 학습 스타일 점수 계산 및 검증
		int learningStyleScore = calculateLearningStyleScore(
			requestDto.conceptualAnswer(),
			requestDto.problemSolvingAnswer(),
			requestDto.preparationAnswer(),
			requestDto.motivationAnswer()
		);

		// 회원 생성
		Member member = Member.builder()
			.email(requestDto.email())
			.nickname(requestDto.nickname())
			.role(Role.USER)
			.socialType(SocialType.KAKAO)
			.socialId(requestDto.socialId())
			.build();

		// 프로필 정보 업데이트
		member.updateProfile(new ProfileUpdateDto(
			requestDto.grade(),
			requestDto.currentUniversity(),
			requestDto.currentMajor(),
			requestDto.targetUniversity(),
			requestDto.targetMajor(),
			learningStyleScore
		));

		return memberRepository.save(member);
	}

	private int calculateLearningStyleScore(int conceptual, int problemSolving, int preparation, int motivation) {
		return conceptual + problemSolving + preparation + motivation;
	}
}
