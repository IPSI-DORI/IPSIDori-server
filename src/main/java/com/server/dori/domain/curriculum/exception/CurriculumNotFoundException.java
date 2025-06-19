package com.server.dori.domain.curriculum.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class CurriculumNotFoundException extends CustomException {
	public CurriculumNotFoundException(Long curriculumId) {
		super(HttpStatus.NOT_FOUND, "Curriculum_404_2", "id가 " + curriculumId + "인 커리큘럼을 찾을 수 없습니다.");
	}
}
