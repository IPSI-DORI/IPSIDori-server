package com.server.dori.domain.curriculum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.dori.domain.curriculum.entity.Curriculum;
import com.server.dori.domain.curriculum.exception.CurriculumNotFoundException;
import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.exception.GradeNotFoundException;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.exception.MemberNotFoundException;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
	default Curriculum getById(Long curriculumId) {
		return findById(curriculumId).orElseThrow(() -> new CurriculumNotFoundException(curriculumId));
	}
}
