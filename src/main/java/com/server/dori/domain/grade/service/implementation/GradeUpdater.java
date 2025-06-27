package com.server.dori.domain.grade.service.implementation;

import org.springframework.stereotype.Service;

import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.presentation.dto.request.GradeRequest;
import com.server.dori.domain.grade.presentation.dto.request.GradeWithCurriculumRequest;
import com.server.dori.domain.grade.repository.GradeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradeUpdater {

	public Grade saveGrade(Grade grade, GradeWithCurriculumRequest request) {
		grade.saveGrade(request);
		return grade;
	}

	public Grade updateGrade(Grade grade, GradeRequest request) {
		grade.updateGrade(request);
		return grade;
	}
}
