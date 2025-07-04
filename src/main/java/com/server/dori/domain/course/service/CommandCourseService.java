package com.server.dori.domain.course.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.course.entity.Course;
import com.server.dori.domain.course.presentation.dto.request.CourseRequest;
import com.server.dori.domain.course.presentation.dto.response.CourseResponse;
import com.server.dori.domain.course.service.implementation.CourseCreator;
import com.server.dori.domain.course.service.implementation.CourseDeleter;
import com.server.dori.domain.course.service.implementation.CourseReader;
import com.server.dori.domain.course.service.implementation.CourseValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandCourseService {

	private final CourseCreator courseCreator;
	private final CourseReader courseReader;
	private final CourseValidator courseValidator;
	private final CourseDeleter courseDeleter;

	public CourseResponse createCourse(CourseRequest request, Long memberId) {
		return CourseResponse.of(courseCreator.createCourse(request, memberId));
	}

	public void deleteCourse(Long memberId, Long courseId) {
		Course course = courseReader.read(courseId);
		courseValidator.checkCourseAuthor(course, memberId);
		courseDeleter.delete(courseId);
	}
}
