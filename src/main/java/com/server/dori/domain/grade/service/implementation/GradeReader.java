package com.server.dori.domain.grade.service.implementation;

import org.springframework.stereotype.Service;

import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.repository.GradeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradeReader {

	private final GradeRepository gradeRepository;

	public Grade read(Long groupId) {
		return gradeRepository.getById(groupId);
	}
}
