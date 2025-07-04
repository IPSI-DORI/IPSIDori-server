package com.server.dori.domain.course.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.course.entity.Course;
import com.server.dori.domain.course.presentation.dto.response.CourseListResponse;
import com.server.dori.domain.course.presentation.dto.response.CourseResponse;
import com.server.dori.domain.course.service.implementation.CourseReader;
import com.server.dori.domain.course.service.implementation.CourseValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryCourseService {

	private final CourseValidator courseValidator;
	private final CourseReader courseReader;

	public CourseResponse getCourse(Long memberId, Long courseId) {
		Course course = courseReader.read(courseId);
		courseValidator.checkGradeAuthor(course, memberId);
		return CourseResponse.of(course);
	}

	public List<CourseListResponse> getCourseList(Long memberId) {
		List<Course> courses = courseReader.readAll(memberId);
		return courses.stream()
			.map(CourseListResponse::of)
			.toList();
	}
}
