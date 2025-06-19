package com.server.dori.domain.curriculum.presentation.dto.request;

import java.util.List;

public record AICurriculumResponse(
	AICourseResponse recommendedCourse,
	List<LectureResponse> lectureList
) {}