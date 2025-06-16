package com.server.dori.domain.curriculum.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.curriculum.presentation.dto.request.CurriculumSurveyRequest;
import com.server.dori.domain.curriculum.presentation.dto.response.CurriculumSurveyResponse;
import com.server.dori.domain.curriculum.entity.Curriculum;
import com.server.dori.domain.curriculum.service.implementation.CurriculumCreator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandCuriculumService {

	private final CurriculumCreator curriculumCreator;

	public CurriculumSurveyResponse saveSurvey(CurriculumSurveyRequest request) {
		return curriculumCreator.saveSurvey(request);
	}
}
