package com.server.dori.domain.grade.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.presentation.dto.request.GradeRequest;
import com.server.dori.domain.grade.presentation.dto.request.GradeWithCurriculumRequest;
import com.server.dori.domain.grade.presentation.dto.response.GradeResponse;
import com.server.dori.domain.grade.service.implementation.GradeCreator;
import com.server.dori.domain.grade.service.implementation.GradeUpdater;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandGradeService {

	private final GradeUpdater gradeUpdater;
	private final GradeCreator gradeCreator;

	public GradeResponse saveGrade(GradeWithCurriculumRequest request, Long gradeId) {
		Grade grade = gradeUpdater.saveGrade(request, gradeId);
		return GradeResponse.from(grade);
	}

	public GradeResponse createGrade(GradeRequest request, Long memberId) {
		Grade grade = gradeCreator.createGrade(request, memberId);
		return GradeResponse.from(grade);
	}
}
