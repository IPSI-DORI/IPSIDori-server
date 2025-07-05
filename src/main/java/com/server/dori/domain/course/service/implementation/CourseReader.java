package com.server.dori.domain.course.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.server.dori.domain.course.entity.Course;
import com.server.dori.domain.course.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseReader {

	private final CourseRepository courseRepository;

	public Course read(Long courseId) {
		return courseRepository.getById(courseId);
	}

	public List<Course> readAll(Long memberId) {
		return courseRepository.findAllByCreatorId(memberId);
	}
}
