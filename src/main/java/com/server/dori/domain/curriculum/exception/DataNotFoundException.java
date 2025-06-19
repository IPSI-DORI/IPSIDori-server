package com.server.dori.domain.curriculum.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class DataNotFoundException extends CustomException {
	public DataNotFoundException() {
		super(HttpStatus.NOT_FOUND, "Curriculum_404_1", "해당 정보를 찾을 수 없습니다.");
	}
}