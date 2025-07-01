package com.server.dori.domain.curriculum.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.server.dori.domain.curriculum.exception.ApiCallException;
import com.server.dori.domain.curriculum.presentation.dto.request.AICurriculumRequest;
import com.server.dori.domain.curriculum.presentation.dto.response.AICurriculumResponse;
import com.server.dori.domain.curriculum.presentation.dto.request.CurriculumSurveyRequest;
import com.server.dori.domain.curriculum.entity.Curriculum;
import com.server.dori.domain.curriculum.presentation.dto.response.CurriculumSurveyResponse;
import com.server.dori.domain.curriculum.repository.CurriculumRepository;
import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.repository.GradeRepository;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.MemberInfo;
import com.server.dori.domain.member.exception.MemberNotFoundException;
import com.server.dori.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurriculumCreator {

	private final CurriculumRepository curriculumRepository;
	private final MemberRepository memberRepository;
	private final GradeRepository gradeRepository;
	private final RestClient restClient;

	public CurriculumSurveyResponse saveSurvey(CurriculumSurveyRequest request, Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberNotFoundException());

		Curriculum curriculum = Curriculum.builder()
			.creator(member)
			.subject(request.subject())
			.elective(request.elective())
			.studyTime(request.studyTime())
			.studyDays(request.studyDays())
			.weakProblemQuestion(request.weakProblemQuestion())
			.learningGoalQuestion(request.learningGoalQuestion())
			.platform(request.platform())
			.build();

		Curriculum savedCurriculum = curriculumRepository.save(curriculum);

		return CurriculumSurveyResponse.of(savedCurriculum);
	}

	public AICurriculumResponse createCurriculum(Long memberId, Long curriculumId) {
		MemberInfo memberInfo = memberRepository.getById(memberId).getMemberInfo();
		Curriculum curriculum = curriculumRepository.getById(curriculumId);
		Grade grade = gradeRepository.getById(curriculumId);

		try {
			AICurriculumRequest request = new AICurriculumRequest(memberInfo, curriculum, grade);
			String query = AICurriculumRequest.toQuery(request);

			AICurriculumResponse response = restClient.get()
				.uri(uriBuilder -> uriBuilder
					.path("/curriculum")
					.queryParam("user_question", query)
					.build())
				.retrieve()
				.body(AICurriculumResponse.class);

			System.out.println(response);
			return response;
		} catch (Exception e) {
			throw new ApiCallException(e.getMessage());
		}
	}
}
