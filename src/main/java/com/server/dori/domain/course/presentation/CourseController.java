package com.server.dori.domain.course.presentation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.dori.domain.course.presentation.dto.request.CourseRequest;
import com.server.dori.domain.course.presentation.dto.response.CourseListResponse;
import com.server.dori.domain.course.presentation.dto.response.CourseResponse;
import com.server.dori.domain.course.service.CommandCourseService;
import com.server.dori.domain.course.service.QueryCourseService;
import com.server.dori.domain.member.entity.CustomUserDetails;
import com.server.dori.global.response.CustomApiResponse;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CourseController implements CourseApiController{

	private final CommandCourseService commandCourseService;
	private final QueryCourseService queryCourseService;

	@Override
	public ResponseEntity<CustomApiResponse<CourseResponse>> createCourse(
		@RequestBody CourseRequest request,
		@AuthenticationPrincipal CustomUserDetails userDetails
	){
		CourseResponse response = commandCourseService.createCourse(request, userDetails.getMemberId());
		return CustomApiResponse.ok(response);
	}

	@Override
	public ResponseEntity<CustomApiResponse<List<CourseListResponse>>> getCourseList(
		@AuthenticationPrincipal CustomUserDetails userDetails
	) {
		List<CourseListResponse> response = queryCourseService.getCourseList(userDetails.getMemberId());
		return CustomApiResponse.ok(response);
	}

	@Override
	public ResponseEntity<CustomApiResponse<CourseResponse>> getCourse(
		@RequestParam(name = "courseId") Long courseId,
		@AuthenticationPrincipal CustomUserDetails userDetails
	) {
		CourseResponse response = queryCourseService.getCourse(userDetails.getMemberId(), courseId);
		return CustomApiResponse.ok(response);
	}
}
