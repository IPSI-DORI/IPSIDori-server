package com.server.dori.domain.course.repository.sub;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.dori.domain.course.entity.sub.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
