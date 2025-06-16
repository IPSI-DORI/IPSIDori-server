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
			"학년 값이 유효하지 않습니다. [HIGH_1, HIGH_2, HIGH_3, RETRY_1, RETRY_2] 중 하나를 입력해주세요.");
	}

	public static MemberInvalidException missingRetryStudentInfo() {
		return new MemberInvalidException(HttpStatus.BAD_REQUEST, "MEMBER_400_4", "재수/반수생은 현재 대학교와 전공을 필수로 입력해야 합니다.");
	}

	public static MemberInvalidException invalidCharacterScore() {
		return new MemberInvalidException(HttpStatus.BAD_REQUEST, "MEMBER_400_5",
			"캐릭터 점수가 유효하지 않습니다. 4점에서 16점 사이의 값이어야 합니다.");
	}
}
