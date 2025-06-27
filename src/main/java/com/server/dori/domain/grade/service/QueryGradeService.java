package com.server.dori.domain.grade.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.presentation.dto.response.GradeResponse;
import com.server.dori.domain.grade.service.implementation.GradeReader;
import com.server.dori.domain.grade.service.implementation.GradeValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QueryGradeService {

	private final GradeReader gradeReader;
	private final GradeValidator gradeValidator;

	public GradeResponse getGrade(Long memberId, Long gradeId) {
		Grade grade = gradeReader.read(gradeId);
		gradeValidator.checkGradeAuthor(grade, memberId);
		return GradeResponse.from(grade);
	}

	public List<GradeResponse> getAllGrades(Long memberId) {
		List<Grade> grades = gradeReader.readAll(memberId);
		return GradeResponse.from(grades);
	}
}
