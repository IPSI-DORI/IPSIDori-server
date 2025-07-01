package com.server.dori.domain.member.exception;

import org.springframework.http.HttpStatus;

import com.server.dori.global.response.exception.CustomException;

public class MemberNotFoundException extends CustomException {
	public MemberNotFoundException() {
		super(HttpStatus.NOT_FOUND, "MEMBER_404_1", "회원을 찾을 수 없습니다.");
	}
}
