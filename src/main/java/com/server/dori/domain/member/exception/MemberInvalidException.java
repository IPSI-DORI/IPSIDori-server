package com.server.dori.domain.member.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class MemberInvalidException extends CustomException {

	public MemberInvalidException(HttpStatus httpStatus, String code, String message) {
		super(httpStatus, code, message);
	}

	public static MemberInvalidException invalidLearningStyleScore() {
		return new MemberInvalidException(HttpStatus.BAD_REQUEST, "MEMBER_400_1", "유효하지 않은 학습 스타일 점수입니다.");
	}

	public static MemberInvalidException signupNotCompleted() {
		return new MemberInvalidException(HttpStatus.BAD_REQUEST, "MEMBER_400_2", "회원가입이 완료되지 않았습니다. 추가 정보를 입력해주세요.");
	}

	public static MemberInvalidException invalidGradeValue() {
		return new MemberInvalidException(HttpStatus.BAD_REQUEST, "MEMBER_400_3",
			"학년 값이 유효하지 않습니다. [HIGH_1, HIGH_2, HIGH_3, JAESU, BANSU] 중 하나를 입력해주세요.");
	}

	public static MemberInvalidException invalidCharacterScore() {
		return new MemberInvalidException(HttpStatus.BAD_REQUEST, "MEMBER_400_4",
			"캐릭터 점수가 유효하지 않습니다. 4점 이상 16점 이하의 값이어야 합니다.");
	}

	public static MemberInvalidException invalidHighSchoolYear() {
		return new MemberInvalidException(HttpStatus.BAD_REQUEST, "MEMBER_400_5",
			"고등학생 학년이 아닙니다. [HIGH_1, HIGH_2, HIGH_3] 중 하나를 입력해주세요.");
	}

	public static MemberInvalidException invalidRetryStudentYear() {
		return new MemberInvalidException(HttpStatus.BAD_REQUEST, "MEMBER_400_6",
			"재수생/반수생 학년이 아닙니다. [JAESU, BANSU] 중 하나를 입력해주세요.");
	}

	public static MemberInvalidException nicknameRequired() {
		return new MemberInvalidException(HttpStatus.BAD_REQUEST, "MEMBER_400_7",
			"닉네임은 필수입니다.");
	}

	public static MemberInvalidException schoolYearRequired() {
		return new MemberInvalidException(HttpStatus.BAD_REQUEST, "MEMBER_400_8",
			"학년은 필수입니다.");
	}

	public static MemberInvalidException targetUniversityRequired() {
		return new MemberInvalidException(HttpStatus.BAD_REQUEST, "MEMBER_400_9",
			"목표 대학교는 필수입니다.");
	}

	public static MemberInvalidException targetMajorRequired() {
		return new MemberInvalidException(HttpStatus.BAD_REQUEST, "MEMBER_400_10",
			"목표 전공은 필수입니다.");
	}

	public static MemberInvalidException learningStyleRequired() {
		return new MemberInvalidException(HttpStatus.BAD_REQUEST, "MEMBER_400_11",
			"학습 스타일은 필수입니다.");
	}
}
