package com.server.dori.domain.curriculum.service.implementation;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.server.dori.domain.curriculum.presentation.dto.request.CurriculumSurveyRequest;
import com.server.dori.domain.curriculum.entity.Curriculum;
import com.server.dori.domain.curriculum.repository.CurriculumRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurriculumCreator {

	private final CurriculumRepository curriculumRepository;

	public Curriculum saveSurvey(CurriculumSurveyRequest request) {
		Curriculum curriculum = Curriculum.builder()
			.subject(request.subject())
			.elective(request.elective())
			.studyTime(request.studyTime())
			.studyDays(request.studyDays())
			.question1(request.question1())
			.question2(request.question2())
			.platform(request.platform())
			.createdAt(LocalDateTime.now())
			.build();

		return curriculumRepository.save(curriculum);
	}
}
