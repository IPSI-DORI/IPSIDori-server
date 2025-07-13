package com.server.dori.domain.member.presentation.dto.request;

import com.server.dori.domain.member.entity.sub.SchoolYear;

/**
 * 회원가입 요청 DTO의 공통 인터페이스
 */
public interface SignupRequest {
	String nickname();

	SchoolYear schoolYear();

	String targetUniversity();

	String targetMajor();

	int conceptualAnswer();

	int problemSolvingAnswer();

	int preparationAnswer();

	int motivationAnswer();

	/**
	 * 현재 대학교 정보 반환 (고등학생의 경우 "해당없음")
	 */
	default String getCurrentUniversity() {
		return "해당없음";
	}

	/**
	 * 현재 전공 정보 반환 (고등학생의 경우 "해당없음")
	 */
	default String getCurrentMajor() {
		return "해당없음";
	}
}
