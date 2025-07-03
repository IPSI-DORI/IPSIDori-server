package com.server.dori.domain.course.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.dori.domain.course.presentation.dto.request.CourseRequest;
import com.server.dori.domain.course.presentation.dto.response.CourseResponse;
import com.server.dori.domain.course.service.CommandCourseService;
import com.server.dori.domain.member.entity.CustomUserDetails;
import com.server.dori.global.response.CustomApiResponse;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CourseController implements CourseApiController{

	private final CommandCourseService commandCourseService;

	@Override
	public ResponseEntity<CustomApiResponse<CourseResponse>> createCourse(
		@RequestBody CourseRequest request,
		@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
	){
		CourseResponse response = commandCourseService.createCourse(request, userDetails.getMemberId());
		return CustomApiResponse.ok(response);
	}
}
