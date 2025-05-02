package com.server.dori.domain.test.exception;

import com.server.dori.global.response.exception.CustomException;
import com.server.dori.global.response.exception.ErrorStatus;

public class TestException extends CustomException {
	public TestException(ErrorStatus errorStatus) {
		super(errorStatus);
	}
}
