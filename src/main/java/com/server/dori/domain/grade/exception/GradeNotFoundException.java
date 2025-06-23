package com.server.dori.domain.grade.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class GradeNotFoundException extends CustomException {
	public GradeNotFoundException() {
		super(HttpStatus.NOT_FOUND, "GRADE_404_1", "해당 성적을 찾을 수 없습니다.");
	}
}
