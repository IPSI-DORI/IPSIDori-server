package com.server.dori.domain.grade.service.implementation;

import org.springframework.stereotype.Service;

import com.server.dori.domain.curriculum.entity.Curriculum;
import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.presentation.dto.request.GradeWithCurriculumRequest;
import com.server.dori.domain.grade.repository.GradeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradeCreator {

	private final GradeRepository gradeRepository;

	public Grade createGradeWithCurriculum(Curriculum curriculum) {
		Grade grade = Grade.builder()
			.curriculum(curriculum.getId())
			.subjects(curriculum.getSubject())
			.elective(curriculum.getElective())
			.build();

		return gradeRepository.save(grade);
	}
}
