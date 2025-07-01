package com.server.dori.domain.grade.service.implementation;

import org.springframework.stereotype.Service;

import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.exception.GradeNotAuthorException;

@Service
public class GradeValidator {
	public void checkGradeAuthor(Grade grade, Long memberId) {
		if (!grade.isCreator(memberId)) {
			throw new GradeNotAuthorException();
		}
	}
}
