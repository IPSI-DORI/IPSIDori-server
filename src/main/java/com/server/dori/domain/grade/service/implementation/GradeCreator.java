package com.server.dori.domain.grade.service.implementation;

import org.springframework.stereotype.Service;

import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.presentation.dto.request.GradeRequest;
import com.server.dori.domain.grade.repository.GradeRepository;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.exception.MemberNotFoundException;
import com.server.dori.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradeCreator {

	private final GradeRepository gradeRepository;
	private final MemberRepository memberRepository;

	public Grade createGrade(GradeRequest request, Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberNotFoundException());

		Grade grade = Grade.builder()
			.creator(member)
			.subjects(request.subjects())
			.elective(request.elective())
			.exam(request.exam())
			.score(request.score())
			.percent(request.percent())
			.grade(request.grade())
			.build();

		return gradeRepository.save(grade);
	}
}
