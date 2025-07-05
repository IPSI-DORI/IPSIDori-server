package com.server.dori.domain.course.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class CourseNotFoundException extends CustomException {
	public CourseNotFoundException() {
		super(HttpStatus.NOT_FOUND, "COURSE_404_1", "해당 강의를 찾을 수 없습니다.");
	}
}
