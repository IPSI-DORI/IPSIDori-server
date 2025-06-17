package com.server.dori.domain.curriculum.service.implementation;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.server.dori.domain.curriculum.presentation.dto.request.CurriculumSurveyRequest;
import com.server.dori.domain.curriculum.entity.Curriculum;
import com.server.dori.domain.curriculum.presentation.dto.response.CurriculumSurveyResponse;
import com.server.dori.domain.curriculum.repository.CurriculumRepository;
import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.service.implementation.GradeCreator;
import com.server.dori.domain.member.entity.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurriculumCreator {

	private final CurriculumRepository curriculumRepository;
	private final GradeCreator gradeCreator;

	public CurriculumSurveyResponse saveSurvey(CurriculumSurveyRequest request, Long memberId) {

		Curriculum curriculum = Curriculum.builder()
			.creator(memberId)
			.subject(request.subject())
			.elective(request.elective())
			.studyTime(request.studyTime())
			.studyDays(request.studyDays())
			.question1(request.question1())
			.question2(request.question2())
			.platform(request.platform())
			.build();

		Curriculum savedCurriculum = curriculumRepository.save(curriculum);
		Grade grade = gradeCreator.createGrade(savedCurriculum);

		return CurriculumSurveyResponse.of(savedCurriculum, grade.getId());
	}
}
