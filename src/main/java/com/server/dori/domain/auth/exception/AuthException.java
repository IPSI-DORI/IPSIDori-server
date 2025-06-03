package com.server.dori.domain.auth.exception;

import com.server.dori.global.response.exception.CustomException;
import com.server.dori.global.response.exception.ErrorStatus;

public class AuthException extends CustomException {
	public AuthException(ErrorStatus errorStatus) {
		super(errorStatus);
	}
}
