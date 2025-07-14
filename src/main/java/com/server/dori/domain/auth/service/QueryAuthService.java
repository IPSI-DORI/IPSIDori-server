package com.server.dori.domain.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.auth.presentation.dto.response.TokenDto;
import com.server.dori.domain.auth.service.implementation.AuthValidator;
import com.server.dori.domain.auth.service.implementation.TokenIssuer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryAuthService {
	private final AuthValidator authValidator;
	private final TokenIssuer tokenIssuer;

	public TokenDto reissue(String refreshToken) {
		authValidator.validateRefreshToken(refreshToken);
		return tokenIssuer.reissueToken(refreshToken);
	}
}
