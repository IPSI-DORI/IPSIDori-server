package com.server.dori.domain.curriculum.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.server.dori.domain.curriculum.presentation.dto.response.AICurriculumResponse;
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

@Tag(name = "커리큘럼", description = "커리큘럼 관련 API")
@RequestMapping("/api/v1/curriculum")
public interface CurriculumApiController {

	@Operation(summary = "커리큘럼 설문 저장", description = "커리큘럼을 생성하기 위한 설문조사를 저장합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "설문 저장 성공",
			content = @Content(schema = @Schema(implementation = CurriculumSurveyResponse.class))),
		@ApiResponse(responseCode = "400", description = "잘못된 요청")
	})
	@PostMapping("/survey")
	ResponseEntity<CustomApiResponse<CurriculumSurveyResponse>> saveSurvey(
		@RequestBody CurriculumSurveyRequest request,
		@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
	);

	@Operation(
		summary = "커리큘럼 생성",
		description = "사용자 정보를 바탕으로 커리큘럼을 생성합니다."
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "커리큘럼 조회 성공",
			content = @Content(schema = @Schema(implementation = AICurriculumResponse.class))),
		@ApiResponse(responseCode = "404", description = "정보를 찾을 수 없음"),
		@ApiResponse(responseCode = "500", description = "외부 API 호출 오류")
	})
	@GetMapping
	ResponseEntity<CustomApiResponse<AICurriculumResponse>> getCurriculum(
		@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails,
		@RequestParam(name = "curriculumId") Long curriculumId
	);
}
