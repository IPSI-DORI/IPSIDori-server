package com.server.dori.domain.grade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.dori.domain.grade.entity.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
