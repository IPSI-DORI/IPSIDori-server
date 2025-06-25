package com.server.dori.domain.grade.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.server.dori.domain.grade.presentation.dto.request.GradeRequest;
import com.server.dori.domain.grade.presentation.dto.response.GradeResponse;
import com.server.dori.global.response.CustomApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "성적", description = "성적 관련 API")
@RequestMapping("/api/v1/grade")
public interface GradeApiController {

	@Operation(summary = "성적 저장", description = "해당 gradeId에 대한 성적 정보를 저장하거나 업데이트합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성적 저장 성공",
			content = @Content(schema = @Schema(implementation = GradeResponse.class))),
		@ApiResponse(responseCode = "400", description = "잘못된 요청"),
		@ApiResponse(responseCode = "404", description = "해당 ID에 해당하는 성적 정보를 찾을 수 없음")
	})
	@PutMapping
	ResponseEntity<CustomApiResponse<GradeResponse>> saveGrade(
		@RequestBody GradeRequest request,
		@Parameter(description = "수정할 Grade의 Id", example = "1") @RequestParam(name = "gradeId") Long gradeId
	);
}