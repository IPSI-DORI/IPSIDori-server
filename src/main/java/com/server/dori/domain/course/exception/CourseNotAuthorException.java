package com.server.dori.domain.course.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class CourseNotAuthorException extends CustomException {
	public CourseNotAuthorException() {
		super(HttpStatus.FORBIDDEN, "GRADE_403_1", "성적 생성자가 아닙니다.");
	}
}
