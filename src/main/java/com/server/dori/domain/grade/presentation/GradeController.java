package com.server.dori.domain.grade.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.dori.domain.grade.presentation.dto.request.GradeRequest;
import com.server.dori.domain.grade.presentation.dto.response.GradeResponse;
import com.server.dori.domain.grade.service.CommandGradeService;
import com.server.dori.global.response.CustomApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/grade")
public class GradeController {

	private final CommandGradeService commandGradeService;

	@PutMapping
	public ResponseEntity<CustomApiResponse<GradeResponse>> saveGrade(@RequestBody GradeRequest request, @RequestParam(name = "gradeId") Long gradeId) {
		GradeResponse response = commandGradeService.saveGrade(request, gradeId);
		return CustomApiResponse.ok(response);
	}
}
