package com.server.dori.domain.member.service.implementation;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.MemberInfo;
import com.server.dori.domain.member.entity.sub.LearningStyle;
import com.server.dori.domain.member.presentation.dto.request.MemberInfoUpdateDto;
import com.server.dori.domain.member.presentation.dto.request.MemberSignupRequestDto;
import com.server.dori.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberUpdater {
	private final MemberRepository memberRepository;

	@Transactional
	public Member updateInfo(Member member, MemberInfoUpdateDto requestDto) {
		MemberInfo memberInfo = getOrCreateMemberInfo(member);

		memberInfo.updateInfo(
			requestDto.nickname(),
			requestDto.grade(),
			requestDto.targetUniversity(),
			requestDto.targetMajor()
		);

		return memberRepository.save(member);
	}

	@Transactional
	public Member updateMemberWithAdditionalInfo(Member member, MemberSignupRequestDto requestDto) {
		MemberInfo memberInfo = getOrCreateMemberInfo(member);

		int learningStyleScore = calculateLearningStyleScore(
			requestDto.conceptualAnswer(),
			requestDto.problemSolvingAnswer(),
			requestDto.preparationAnswer(),
			requestDto.motivationAnswer()
		);

		memberInfo.updateForSignup(
			requestDto.nickname(),
			requestDto.grade(),
			requestDto.currentUniversity(),
			requestDto.currentMajor(),
			requestDto.targetUniversity(),
			requestDto.targetMajor(),
			LearningStyle.fromScore(learningStyleScore),
			learningStyleScore
		);

		return memberRepository.save(member);
	}

	private MemberInfo getOrCreateMemberInfo(Member member) {
		if (member.getMemberInfo() == null) {
			member.initializeMemberInfo();
		}
		return member.getMemberInfo();
	}

	private int calculateLearningStyleScore(int conceptual, int problemSolving, int preparation, int motivation) {
		return conceptual + problemSolving + preparation + motivation;
	}
}
