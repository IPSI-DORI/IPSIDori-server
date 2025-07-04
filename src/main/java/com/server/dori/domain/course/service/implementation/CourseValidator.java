package com.server.dori.domain.course.service.implementation;

import org.springframework.stereotype.Service;

import com.server.dori.domain.course.entity.Course;
import com.server.dori.domain.course.entity.sub.Lecture;
import com.server.dori.domain.course.exception.CourseNotAuthorException;

@Service
public class CourseValidator {
	public void validateLectureCourseBinding(Lecture lecture) {
		if (lecture.hasCourse()) {
			throw new IllegalStateException("이미 Course에 할당된 Lecture입니다.");
		}
	}

	public void checkGradeAuthor(Course course, Long memberId) {
		if (!course.isCreator(memberId)) {
			throw new CourseNotAuthorException();
		}
	}
}
