package com.server.dori.domain.course.service.implementation;

import org.springframework.stereotype.Service;

import com.server.dori.domain.course.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseDeleter {

	private final CourseRepository courseRepository;

	public void delete(Long courseId) {
		courseRepository.deleteById(courseId);
	}
}
