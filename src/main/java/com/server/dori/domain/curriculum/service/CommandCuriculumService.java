package com.server.dori.domain.curriculum.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.curriculum.presentation.dto.response.AICurriculumResponse;
import com.server.dori.domain.curriculum.presentation.dto.request.CurriculumSurveyRequest;
import com.server.dori.domain.curriculum.presentation.dto.response.CurriculumSurveyResponse;
import com.server.dori.domain.curriculum.service.implementation.CurriculumCreator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandCuriculumService {

	private final CurriculumCreator curriculumCreator;

	public CurriculumSurveyResponse saveSurvey(CurriculumSurveyRequest request, Long memberId) {
		return CurriculumSurveyResponse.of(curriculumCreator.saveSurvey(request, memberId));
	}

	public AICurriculumResponse createCurriculum(Long memberId, Long curriculumId, Long gradeId) {
		return curriculumCreator.createCurriculum(memberId, curriculumId, gradeId);
	}
}
