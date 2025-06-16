package com.server.dori.domain.curriculum.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dori.domain.curriculum.presentation.dto.request.CurriculumSurveyRequest;
import com.server.dori.domain.curriculum.presentation.dto.response.CurriculumSurveyResponse;
import com.server.dori.domain.curriculum.service.CommandCuriculumService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/curriculum")
public class CurriculumController {
	private final CommandCuriculumService commandCuriculumService;

	@PostMapping("/survey")
	public ResponseEntity<CurriculumSurveyResponse> saveSurvey(@RequestBody CurriculumSurveyRequest request) {
		CurriculumSurveyResponse response = commandCuriculumService.saveSurvey(request);
		return ResponseEntity.ok(response);
	}
}
