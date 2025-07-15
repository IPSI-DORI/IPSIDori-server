package com.server.dori.domain.member.service.implementation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.MemberInfo;
import com.server.dori.domain.member.entity.sub.LearningStyle;
import com.server.dori.domain.member.presentation.dto.request.MemberInfoUpdate;
import com.server.dori.domain.member.presentation.dto.request.SignupRequest;
import com.server.dori.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberUpdater {
	private final MemberRepository memberRepository;

	public Member updateInfo(Member member, MemberInfoUpdate requestDto) {
		MemberInfo memberInfo = getOrCreateMemberInfo(member);

		memberInfo.updateInfo(
			requestDto.nickname(),
			requestDto.schoolYear(),
			requestDto.targetUniversity(),
			requestDto.targetMajor()
		);

		return memberRepository.save(member);
	}

	public Member updateMemberWithAdditionalInfo(Member member, SignupRequest requestDto) {
		MemberInfo memberInfo = getOrCreateMemberInfo(member);

		int learningStyleScore = calculateLearningStyleScore(
			requestDto.conceptualAnswer(),
			requestDto.problemSolvingAnswer(),
			requestDto.preparationAnswer(),
			requestDto.motivationAnswer()
		);

		memberInfo.updateForSignup(
			requestDto.nickname(),
			requestDto.schoolYear(),
			requestDto.getCurrentUniversity(),
			requestDto.getCurrentMajor(),
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
