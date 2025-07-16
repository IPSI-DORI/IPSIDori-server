package com.server.dori.domain.course.service.implementation;

import org.springframework.stereotype.Service;

import com.server.dori.domain.course.entity.Course;
import com.server.dori.domain.course.entity.sub.Lecture;
import com.server.dori.domain.course.exception.CourseNotFoundException;
import com.server.dori.domain.course.repository.sub.LectureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseUpdater {
	private final LectureRepository lectureRepository;
	private final CourseValidator courseValidator;
	private final CourseReader courseReader;

	public void updateLectureCheck(Long lectureId, boolean isCheck, Long memberId) {
		Lecture lecture = lectureRepository.findById(lectureId)
			.orElseThrow(() -> CourseNotFoundException.lectureNotFoundException());

		Course course = courseReader.read(lecture.getCourse().getId());

		courseValidator.checkCourseAuthor(course, memberId);

		if (lecture.isCheck() != isCheck) {
			lecture.updateCheck(isCheck);

			if (isCheck) {
				course.increaseCheckCount();
			} else {
				course.decreaseCheckCount();
			}
		}
	}
}
