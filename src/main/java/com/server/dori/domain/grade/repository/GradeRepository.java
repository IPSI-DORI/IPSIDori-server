package com.server.dori.domain.grade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.exception.GradeNotFoundException;

public interface GradeRepository extends JpaRepository<Grade, Long> {
	default Grade getById(Long gradeId) {
		return findById(gradeId).orElseThrow(() -> new GradeNotFoundException(gradeId));
	}
}
