package com.server.dori.course.entity;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.server.dori.domain.course.entity.Course;
import com.server.dori.domain.course.entity.sub.Lecture;
import com.server.dori.domain.course.service.implementation.CourseValidator;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.sub.Role;
import com.server.dori.domain.member.entity.sub.SocialType;

class CourseTest {

	@DisplayName("강의를 정상적으로 저장한다.")
	@Test
	void testCourseBuilder() {
		// given
		Long id = 1L;
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
			.id(id)
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
		assertThat(course.getId()).isEqualTo(id);
		assertThat(course.getTitle()).isEqualTo(title);
		assertThat(course.getDescription()).isEqualTo(description);
		assertThat(course.getSubject()).isEqualTo(subject);
		assertThat(course.getTeacher()).isEqualTo(teacher);
		assertThat(course.getGrade()).isEqualTo(grade);
		assertThat(course.getPlatform()).isEqualTo(platform);
		assertThat(course.getPrice()).isEqualTo(price);
		assertThat(course.getRecommend()).isEqualTo(recommend);
	}

	@DisplayName("강의에 강좌를 정상적으로 추가한다.")
	@Test
	void testAddLecture() {
		// given
		Member member = Member.builder()
			.email("test@example.com")
			.role(Role.USER)
			.socialType(SocialType.KAKAO)
			.build();

		Course course = Course.builder()
			.creator(member)
			.title("테스트 강의")
			.build();

		Lecture lecture = Lecture.builder()
			.title("1강 OT")
			.build();

		CourseValidator mockValidator = new CourseValidator() {
			boolean called = false;

			@Override
			public void validateLectureCourseBinding(Lecture lecture) {
				assertThat(lecture).isEqualTo(lecture);
				called = true;
			}
		};

		// when
		course.addLecture(lecture, mockValidator);

		// then
		List<Lecture> lectures = course.getLectureList();
		assertThat(lectures).hasSize(1);
		assertThat(lectures.get(0)).isEqualTo(lecture);
		assertThat(lecture.getCourse()).isEqualTo(course);
	}

	@DisplayName("Course 생성자가 맞는지 확인한다.")
	@Test
	void testIsCreator() throws NoSuchFieldException, IllegalAccessException {
		// given
		Member member = Member.builder()
			.email("test@example.com")
			.role(Role.USER)
			.socialType(SocialType.KAKAO)
			.build();

		Field idField = Member.class.getDeclaredField("id");
		idField.setAccessible(true);
		idField.set(member, 1L);

		Course course = Course.builder()
			.creator(member)
			.title("테스트 강의")
			.build();

		// when & then
		assertThat(course.isCreator(1L)).isTrue();
		assertThat(course.isCreator(2L)).isFalse();
	}
}
