package com.server.dori.domain.grade.service.implementation;

import org.springframework.stereotype.Service;

import com.server.dori.domain.grade.repository.GradeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradeDeleter {
	private final GradeRepository gradeRepository;

	public void delete(Long gradeId) {
		gradeRepository.deleteById(gradeId);
	}
}
