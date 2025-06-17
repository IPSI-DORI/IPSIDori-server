package com.server.dori.domain.curriculum.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.curriculum.presentation.dto.request.CurriculumSurveyRequest;
import com.server.dori.domain.curriculum.presentation.dto.response.CurriculumSurveyResponse;
import com.server.dori.domain.curriculum.entity.Curriculum;
import com.server.dori.domain.curriculum.service.implementation.CurriculumCreator;
import com.server.dori.domain.member.entity.CustomUserDetails;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.MemberInfo;
import com.server.dori.domain.member.service.implementation.MemberValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandCuriculumService {

	private final CurriculumCreator curriculumCreator;
	private final MemberValidator memberValidator;

	public CurriculumSurveyResponse saveSurvey(CurriculumSurveyRequest request, Long memberId) {
		return curriculumCreator.saveSurvey(request, memberId);
	}
}
