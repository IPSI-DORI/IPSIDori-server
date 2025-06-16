package com.server.dori.domain.grade.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class GradeNotFoundException extends CustomException {
	public GradeNotFoundException(Long gradeId) {
		super(HttpStatus.NOT_FOUND, "GRADE_404_1", "아이디가 " + gradeId + "인 성적을 찾을 수 없습니다.");
	}
}
