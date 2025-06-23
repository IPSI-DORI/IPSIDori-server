package com.server.dori.domain.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.dori.domain.course.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
