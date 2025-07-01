package com.server.dori.domain.grade.service.implementation;

import org.springframework.stereotype.Service;

import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.presentation.dto.request.GradeRequest;
import com.server.dori.domain.grade.repository.GradeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradeCreator {

	private final GradeRepository gradeRepository;

	public Grade createGrade(GradeRequest request, Long memberId) {
		Grade grade = Grade.builder()
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
