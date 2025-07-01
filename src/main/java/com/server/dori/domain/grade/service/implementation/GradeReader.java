package com.server.dori.domain.grade.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.exception.GradeNotFoundException;
import com.server.dori.domain.grade.repository.GradeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradeReader {

	private final GradeRepository gradeRepository;

	public Grade read(Long gradeId) {
		return gradeRepository.getById(gradeId);
	}

	public List<Grade> readAll(Long memberId) {
		return gradeRepository.findAllByCreatorId(memberId)
			.orElseThrow(() -> new GradeNotFoundException());
	}
}
