package com.server.dori.domain.course.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.course.presentation.dto.request.CourseRequest;
import com.server.dori.domain.course.presentation.dto.response.CourseResponse;
import com.server.dori.domain.course.service.implementation.CourseCreator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandCourseService {

	private final CourseCreator courseCreator;

	public CourseResponse createCourse(CourseRequest request, Long memberId) {
		return CourseResponse.of(courseCreator.createCourse(request, memberId));
	}
}
