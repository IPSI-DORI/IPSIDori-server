package com.server.dori.course.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.server.dori.domain.course.entity.Course;

class CourseTest {

	@DisplayName("강의를 정상적으로 저장한다.")
	@Test
	void testCourseBuilder() {
		// given
		Long courseId = 1L;
		String title = "[2025 수능완성] 문학/비문학 - 홍길동전 (실전편)";
		String description = "문학·비문학 개념 완성 강의";
		String subject = "국어";
		String teacher = "홍길동";
		String grade = "고1";
		String platform = "EBSi";
		int price = 0;
		String recommend = "문학에 약한 학생에게 추천";

		// when
		Course course = Course.builder()
			.courseId(courseId)
			.title(title)
			.description(description)
			.subject(subject)
			.teacher(teacher)
			.grade(grade)
			.platform(platform)
			.price(price)
			.recommend(recommend)
			.build();

		// then
		assertThat(course.getCourseId()).isEqualTo(courseId);
		assertThat(course.getTitle()).isEqualTo(title);
		assertThat(course.getDescription()).isEqualTo(description);
		assertThat(course.getSubject()).isEqualTo(subject);
		assertThat(course.getTeacher()).isEqualTo(teacher);
		assertThat(course.getGrade()).isEqualTo(grade);
		assertThat(course.getPlatform()).isEqualTo(platform);
		assertThat(course.getPrice()).isEqualTo(price);
		assertThat(course.getRecommend()).isEqualTo(recommend);
	}
}