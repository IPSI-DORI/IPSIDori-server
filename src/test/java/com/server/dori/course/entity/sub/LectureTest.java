package com.server.dori.course.entity.sub;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.server.dori.domain.course.entity.Course;
import com.server.dori.domain.course.entity.sub.Lecture;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.sub.Role;
import com.server.dori.domain.member.entity.sub.SocialType;

class LectureTest {

	@DisplayName("강의가 정상적으로 매핑되는지 확인한다.")
	@Test
	void testLectureBuilder() {
		// given
		String title = "1강 - 고전소설 개념";
		String info = "홍길동전 중심으로 배우는 고전소설 개념";

		// when
		Lecture lecture = Lecture.builder()
			.title(title)
			.info(info)
			.build();

		// then
		assertThat(lecture.getTitle()).isEqualTo(title);
		assertThat(lecture.getInfo()).isEqualTo(info);
		assertThat(lecture.hasCourse()).isFalse();
	}

	@DisplayName("메서드로 강의에 과정을 할당할 수 있다.")
	@Test
	void testAssignCourse() throws Exception {
		// given
		Lecture lecture = Lecture.builder()
			.title("1강 - 문학 개념")
			.info("개념 정리 강의")
			.build();

		Member member = createMemberWithId(1L);
		Course course = Course.builder()
			.creator(member)
			.title("테스트 강의")
			.build();

		// when
		lecture.assignCourse(course);

		// then
		assertThat(lecture.hasCourse()).isTrue();
		assertThat(lecture.getCourse()).isEqualTo(course);
	}

	private Member createMemberWithId(Long id) throws Exception {
		Member member = Member.builder()
			.email("test@example.com")
			.role(Role.USER)
			.socialType(SocialType.KAKAO)
			.build();

		Field idField = Member.class.getDeclaredField("id");
		idField.setAccessible(true);
		idField.set(member, id);
		return member;
	}
}
