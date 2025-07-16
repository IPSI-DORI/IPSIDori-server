package com.server.dori.domain.course.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class CourseNotFoundException extends CustomException {

	public CourseNotFoundException(HttpStatus httpStatus, String code, String message) {
		super(httpStatus, code, message);
	}

	public static CourseNotFoundException courseNotFoundException() {
		return new CourseNotFoundException(HttpStatus.NOT_FOUND, "COURSE_404_1", "해당 강의를 찾을 수 없습니다.");
	}

	public static CourseNotFoundException lectureNotFoundException() {
		return new CourseNotFoundException(HttpStatus.NOT_FOUND, "COURSE_404_2", "해당 투두를 찾을 수 없습니다.");
	}
}
