package com.server.dori.domain.member.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class MemberConflictException extends CustomException {

	public MemberConflictException(HttpStatus httpStatus, String code, String message) {
		super(httpStatus, code, message);
	}

	public static MemberConflictException signupAlreadyCompleted() {
		return new MemberConflictException(HttpStatus.CONFLICT, "MEMBER_409_1", "이미 가입된 회원입니다.");
	}
}
