package com.server.dori.domain.curriculum.presentation.dto.response;

import java.util.List;

public record AICurriculumResponse(
	AICourseResponse recommendedCourse,
	List<LectureResponse> lectureList
) {}