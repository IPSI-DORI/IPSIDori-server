package com.server.dori.domain.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.dori.domain.course.entity.Course;
import com.server.dori.domain.course.exception.CourseNotFoundException;

public interface CourseRepository extends JpaRepository<Course, Long> {
	default Course getById(Long courseId) {
		return findById(courseId).orElseThrow(() -> CourseNotFoundException.courseNotFoundException());
	}

	List<Course> findAllByCreatorId(Long creatorId);
}
