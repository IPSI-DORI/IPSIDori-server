package com.server.dori.domain.curriculum.presentation;

import com.server.dori.domain.curriculum.exception.ApiCallException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.dori.domain.curriculum.presentation.dto.response.AICurriculumListResponse;
import com.server.dori.domain.curriculum.presentation.dto.request.CurriculumSurveyRequest;
import com.server.dori.domain.curriculum.presentation.dto.response.CurriculumSurveyResponse;
import com.server.dori.domain.curriculum.service.CommandCuriculumService;
import com.server.dori.domain.member.entity.CustomUserDetails;
import com.server.dori.global.response.CustomApiResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RestController
@RequiredArgsConstructor
public class CurriculumController implements CurriculumApiController {
	private final CommandCuriculumService commandCuriculumService;
	private final RestClient restClient;

	@Override
	public ResponseEntity<CustomApiResponse<CurriculumSurveyResponse>> saveSurvey(
		@RequestBody CurriculumSurveyRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
		CurriculumSurveyResponse response = commandCuriculumService.saveSurvey(request, userDetails.getMemberId());
		return CustomApiResponse.ok(response);
	}

	@Override
	public ResponseEntity<CustomApiResponse<AICurriculumListResponse>> getCurriculumList(
		@AuthenticationPrincipal CustomUserDetails userDetails,
		@RequestParam(name = "curriculumId") Long curriculumId,
		@RequestParam(name = "gradeId") Long gradeId
	) {
		AICurriculumListResponse response = commandCuriculumService.createCurriculum(userDetails.getMemberId(), curriculumId, gradeId);
		return CustomApiResponse.ok(response);
	}

	@GetMapping("/test")
	public ResponseEntity<String> getAIRoot() {
		try {
			String response = restClient.get()
					.uri("/")
					.retrieve()
					.body(String.class);

			System.out.println(response);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			throw new ApiCallException(e.getMessage());
		}
	}
}
