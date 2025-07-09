package com.server.dori.domain.curriculum.presentation.dto.request;

import com.server.dori.domain.curriculum.entity.Curriculum;
import com.server.dori.domain.curriculum.exception.DataNotFoundException;
import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.member.entity.MemberInfo;

public record AICurriculumRequest(MemberInfo memberInfo, Curriculum curriculum, Grade grade) {

	public static String toQuery(AICurriculumRequest request) {
		MemberInfo member = request.memberInfo();
		Curriculum curriculum = request.curriculum();
		Grade grade = request.grade();

		return String.format(
			"나는 지금부터 강의 추천을 받을거야. 괄호 안의 정보 바탕으로 강의 추천 해줘. " +
				"과목: %s (%s), " +
				"학년: %s (%s, %s), " +
				"현재 나의 성적: %d등급, " +
				"하루 공부 시간: %s, " +
				"공부요일: [%s], " +
				"나의 문제점: %s, " +
				"목표: %s, " +
				"플랫폼: %s",
			curriculum.getSubject(),
			curriculum.getElective(),
			member.getGrade() != null ? member.getGrade().name() : NotFoundException(),
			member.getTargetUniversity() != null ? member.getTargetUniversity() : NotFoundException(),
			member.getTargetMajor() != null ? member.getTargetMajor() : NotFoundException(),
			grade.getGrade(),
			curriculum.getStudyTime() != null ? curriculum.getStudyTime() : NotFoundException(),
			curriculum.getStudyDays() != null ? curriculum.getStudyDays() : NotFoundException(),
			curriculum.getWeakProblemQuestion() != null ? curriculum.getWeakProblemQuestion() : NotFoundException(),
			curriculum.getLearningGoalQuestion() != null ? curriculum.getLearningGoalQuestion() : NotFoundException(),
			curriculum.getPlatform() != null ? curriculum.getPlatform().name() : NotFoundException()
		);
	}

	public static DataNotFoundException NotFoundException(){
		return new DataNotFoundException();
	}
}