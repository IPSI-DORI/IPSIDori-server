package com.server.dori.domain.course.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dori.domain.course.presentation.dto.request.CourseRequest;
import com.server.dori.domain.course.presentation.dto.response.CourseResponse;
import com.server.dori.domain.curriculum.presentation.dto.request.CurriculumSurveyRequest;
import com.server.dori.domain.curriculum.presentation.dto.response.CurriculumSurveyResponse;
import com.server.dori.domain.member.entity.CustomUserDetails;
import com.server.dori.global.response.CustomApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "강의", description = "강의 관련 API")
@RequestMapping("/api/v1/course")
public interface CourseApiController {

	@Operation(summary = "강의 저장", description = "강의를 저장합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "강의 저장 성공",
			content = @Content(schema = @Schema(implementation = CurriculumSurveyResponse.class))),
		@ApiResponse(responseCode = "400", description = "잘못된 요청")
	})
	@PostMapping
	ResponseEntity<CustomApiResponse<CourseResponse>> createCourse(
		@RequestBody CourseRequest request,
		@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
	);
}
