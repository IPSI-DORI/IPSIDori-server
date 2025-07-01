package com.server.dori.domain.grade.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.presentation.dto.request.GradeRequest;
import com.server.dori.domain.grade.presentation.dto.response.GradeResponse;
import com.server.dori.domain.grade.service.implementation.GradeCreator;
import com.server.dori.domain.grade.service.implementation.GradeDeleter;
import com.server.dori.domain.grade.service.implementation.GradeReader;
import com.server.dori.domain.grade.service.implementation.GradeUpdater;
import com.server.dori.domain.grade.service.implementation.GradeValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandGradeService {

	private final GradeUpdater gradeUpdater;
	private final GradeCreator gradeCreator;
	private final GradeReader gradeReader;
	private final GradeValidator gradeValidator;
	private final GradeDeleter gradeDeleter;

	public GradeResponse createGrade(GradeRequest request, Long memberId) {
		Grade grade = gradeCreator.createGrade(request, memberId);
		return GradeResponse.from(grade);
	}

	public GradeResponse updateGrade(GradeRequest request, Long gradeId, Long memberId) {
		Grade grade = gradeReader.read(gradeId);
		gradeValidator.checkGradeAuthor(grade, memberId);
		Grade updatedGrade = gradeUpdater.updateGrade(grade, request);
		return GradeResponse.from(updatedGrade);
	}

	public void deleteGrade(Long gradeId, Long memberId) {
		Grade grade = gradeReader.read(gradeId);
		gradeValidator.checkGradeAuthor(grade, memberId);
		gradeDeleter.delete(gradeId);
	}
}
