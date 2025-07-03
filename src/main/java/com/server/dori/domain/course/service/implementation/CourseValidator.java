package com.server.dori.domain.course.service.implementation;

import org.springframework.stereotype.Service;

import com.server.dori.domain.course.entity.sub.Lecture;

@Service
public class CourseValidator {
	public void validateLectureCourseBinding(Lecture lecture) {
		if (lecture.hasCourse()) {
			throw new IllegalStateException("이미 Course에 할당된 Lecture입니다.");
		}
	}
}
