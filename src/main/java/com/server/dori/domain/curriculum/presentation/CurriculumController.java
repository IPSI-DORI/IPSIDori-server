package com.server.dori.domain.curriculum.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.dori.domain.curriculum.presentation.dto.response.AICurriculumResponse;
import com.server.dori.domain.curriculum.presentation.dto.request.CurriculumSurveyRequest;
import com.server.dori.domain.curriculum.presentation.dto.response.CurriculumSurveyResponse;
import com.server.dori.domain.curriculum.service.CommandCuriculumService;
import com.server.dori.domain.member.entity.CustomUserDetails;
import com.server.dori.global.response.CustomApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CurriculumController implements CurriculumApiController {
	private final CommandCuriculumService commandCuriculumService;

	@Override
	public ResponseEntity<CustomApiResponse<CurriculumSurveyResponse>> saveSurvey(
		@RequestBody CurriculumSurveyRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
		CurriculumSurveyResponse response = commandCuriculumService.saveSurvey(request, userDetails.getMemberId());
		return CustomApiResponse.ok(response);
	}

	@Override
	public ResponseEntity<CustomApiResponse<AICurriculumResponse>> getCurriculum(
		@AuthenticationPrincipal CustomUserDetails userDetails,
		@RequestParam(name = "curriculumId") Long curriculumId
	) {
		AICurriculumResponse response = commandCuriculumService.createCurriculum(userDetails.getMemberId(), curriculumId);
		return CustomApiResponse.ok(response);
	}
}
