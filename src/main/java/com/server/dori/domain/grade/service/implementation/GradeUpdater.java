package com.server.dori.domain.grade.service.implementation;

import org.springframework.stereotype.Service;

import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.presentation.dto.request.GradeRequest;
import com.server.dori.domain.grade.repository.GradeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradeUpdater {

	private final GradeRepository gradeRepository;

	public Grade saveGrade(GradeRequest request, Long gradeId) {
		Grade grade = gradeRepository.getById(gradeId);
		grade.saveGrade(request);
		return grade;
	}
}
