package com.server.dori.domain.course.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.server.dori.domain.course.entity.Course;
import com.server.dori.domain.course.entity.sub.Lecture;
import com.server.dori.domain.course.presentation.dto.request.CourseRequest;
import com.server.dori.domain.course.repository.CourseRepository;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.exception.MemberNotFoundException;
import com.server.dori.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseCreator {

	private final MemberRepository memberRepository;
	private final CourseValidator courseValidator;
	private final CourseRepository courseRepository;

	public Course createCourse(CourseRequest request, Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberNotFoundException());

		CourseRequest.CourseDto courseDto = request.recommendedCourse();

		Course course = Course.builder()
			.id(member.getId())
			.title(courseDto.title())
			.description(courseDto.description())
			.subject(courseDto.subject())
			.teacher(courseDto.teacher())
			.grade(courseDto.grade())
			.platform(courseDto.platform())
			.price(courseDto.price())
			.recommend(courseDto.recommend())
			.build();

		List<CourseRequest.LectureDto> lectureDtoList = request.lectureList();
		for (CourseRequest.LectureDto dto : lectureDtoList) {
			Lecture lecture = Lecture.builder()
				.title(dto.title())
				.info(dto.info())
				.build();
			course.addLecture(lecture, courseValidator);
		}

		return courseRepository.save(course);
	}
}
