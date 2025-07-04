package com.server.dori.domain.course.presentation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.server.dori.domain.course.presentation.dto.request.CourseRequest;
import com.server.dori.domain.course.presentation.dto.response.CourseListResponse;
import com.server.dori.domain.course.presentation.dto.response.CourseResponse;
import com.server.dori.domain.curriculum.presentation.dto.response.CurriculumSurveyResponse;
import com.server.dori.domain.member.entity.CustomUserDetails;
import com.server.dori.global.response.CustomApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "강의", description = "강의 관련 API")
@RequestMapping("/api/v1/course")
public interface CourseApiController {

	@Operation(summary = "강의 전체 조회", description = "학생의 강의 정보를 전체 조회합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "강의 전체 조회 성공",
			content = @Content(schema = @Schema(implementation = CourseListResponse.class)))
	})
	@GetMapping
	ResponseEntity<CustomApiResponse<List<CourseListResponse>>> getCourseList(
		@AuthenticationPrincipal CustomUserDetails userDetails
	);

	@Operation(summary = "강의 상세 조회", description = "특정 강의 Id로 강의 상세 정보를 조회합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "강의 상세 조회 성공",
			content = @Content(schema = @Schema(implementation = CourseResponse.class))),
		@ApiResponse(responseCode = "404", description = "강의 없음")
	})
	@GetMapping("/{courseId}")
	ResponseEntity<CustomApiResponse<CourseResponse>> getCourse(
		@RequestParam(name = "courseId") Long courseId,
		@AuthenticationPrincipal CustomUserDetails userDetails
	);

	@Operation(summary = "강의 저장", description = "강의를 저장합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "강의 저장 성공",
			content = @Content(schema = @Schema(implementation = CurriculumSurveyResponse.class))),
		@ApiResponse(responseCode = "400", description = "잘못된 요청")
	})
	@PostMapping
	ResponseEntity<CustomApiResponse<CourseResponse>> createCourse(
		@RequestBody CourseRequest request,
		@AuthenticationPrincipal CustomUserDetails userDetails
	);

	@Operation(summary = "강의 삭제", description = "강의를 삭제합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "강의 삭제 성공",
			content = @Content(schema = @Schema(implementation = CurriculumSurveyResponse.class))),
		@ApiResponse(responseCode = "400", description = "잘못된 요청")
	})
	@DeleteMapping
	ResponseEntity<CustomApiResponse> deleteCourse(
		@RequestParam(name = "courseId") Long courseId,
		@AuthenticationPrincipal CustomUserDetails userDetails
	);
}
