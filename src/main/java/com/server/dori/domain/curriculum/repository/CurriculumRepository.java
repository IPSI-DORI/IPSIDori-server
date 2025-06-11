package com.server.dori.domain.curriculum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.dori.domain.curriculum.entity.Curriculum;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
}
