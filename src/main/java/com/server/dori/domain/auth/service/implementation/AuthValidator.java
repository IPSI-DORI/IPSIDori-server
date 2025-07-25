package com.server.dori.domain.auth.service.implementation;

import org.springframework.stereotype.Service;

import com.server.dori.domain.auth.exception.AuthUnauthorizedException;
import com.server.dori.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthValidator {
	private final JwtTokenProvider jwtTokenProvider;

	public void validateRefreshToken(String refreshToken) {
		try {
			jwtTokenProvider.validateToken(refreshToken);
		} catch (Exception e) {
			throw AuthUnauthorizedException.invalidToken();
		}
	}
}
