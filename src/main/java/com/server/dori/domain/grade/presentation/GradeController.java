package com.server.dori.domain.grade.presentation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.dori.domain.grade.presentation.dto.request.GradeRequest;
import com.server.dori.domain.grade.presentation.dto.request.GradeWithCurriculumRequest;
import com.server.dori.domain.grade.presentation.dto.response.GradeResponse;
import com.server.dori.domain.grade.service.CommandGradeService;
import com.server.dori.domain.grade.service.QueryGradeService;
import com.server.dori.domain.member.entity.CustomUserDetails;
import com.server.dori.global.response.CustomApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GradeController implements GradeApiController {

	private final CommandGradeService commandGradeService;
	private final QueryGradeService queryGradeService;

	@Override
	public ResponseEntity<CustomApiResponse<GradeResponse>> saveGrade(GradeWithCurriculumRequest request,
		Long gradeId) {
		GradeResponse response = commandGradeService.saveGrade(request, gradeId);
		return CustomApiResponse.ok(response);
	}

	@Override
	public ResponseEntity<CustomApiResponse<GradeResponse>> createGrade(
		@RequestBody GradeRequest request,
		@AuthenticationPrincipal CustomUserDetails userDetails
	) {
		GradeResponse response = commandGradeService.createGrade(request, userDetails.getMemberId());
		return CustomApiResponse.ok(response);
	}

	@Override
	public ResponseEntity<CustomApiResponse<GradeResponse>> updateGrade(
		@RequestBody GradeRequest request,
		Long gradeId,
		@AuthenticationPrincipal CustomUserDetails userDetails
	) {
		GradeResponse response = commandGradeService.updateGrade(request, gradeId, userDetails.getMemberId());
		return CustomApiResponse.ok(response);
	}

	@Override
	public ResponseEntity<CustomApiResponse<GradeResponse>> readGrade(
		@RequestParam(name = "gradeId") Long gradeId,
		@AuthenticationPrincipal CustomUserDetails userDetails
	){
		GradeResponse response = queryGradeService.getGrade(gradeId, userDetails.getMemberId());
		return CustomApiResponse.ok(response);
	}

	@Override
	public ResponseEntity<CustomApiResponse<List<GradeResponse>>> readAllGrades(
		@AuthenticationPrincipal CustomUserDetails userDetails
	){
		List<GradeResponse> response = queryGradeService.getAllGrades(userDetails.getMemberId());
		return CustomApiResponse.ok(response);
	}

	@Override
	public ResponseEntity<CustomApiResponse> deleteGrade(
		@RequestParam(name = "gradeId") Long gradeId,
		@AuthenticationPrincipal CustomUserDetails userDetails
	){
		commandGradeService.deleteGrade(gradeId, userDetails.getMemberId());
		return ResponseEntity.noContent().build();
	}
}