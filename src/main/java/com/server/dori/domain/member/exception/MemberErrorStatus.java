package com.server.dori.domain.member.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.ErrorStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorStatus implements ErrorStatus {
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_404_1", "회원을 찾을 수 없습니다."),
	DUPLICATE_EMAIL(HttpStatus.CONFLICT, "MEMBER_409_1", "이미 사용 중인 이메일입니다."),
	INVALID_LEARNING_STYLE_SCORE(HttpStatus.BAD_REQUEST, "MEMBER_400_1", "유효하지 않은 학습 스타일 점수입니다."),
	INVALID_SUBJECT_COMBINATION(HttpStatus.BAD_REQUEST, "MEMBER_400_2", "유효하지 않은 과목 조합입니다."),
	INVALID_GRADE(HttpStatus.BAD_REQUEST, "MEMBER_400_3", "유효하지 않은 학년입니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
