package com.server.dori.domain.grade.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.server.dori.domain.grade.presentation.dto.request.GradeWithCurriculumRequest;
import com.server.dori.domain.grade.presentation.dto.response.GradeResponse;
import com.server.dori.domain.grade.service.CommandGradeService;
import com.server.dori.global.response.CustomApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GradeController implements GradeApiController {

	private final CommandGradeService commandGradeService;

	@Override
	public ResponseEntity<CustomApiResponse<GradeResponse>> saveGrade(GradeWithCurriculumRequest request, Long gradeId) {
		GradeResponse response = commandGradeService.saveGrade(request, gradeId);
		return CustomApiResponse.ok(response);
	}
}