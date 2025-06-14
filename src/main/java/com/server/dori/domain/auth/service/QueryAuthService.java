package com.server.dori.domain.auth.service;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.auth.exception.AuthNotFoundException;
import com.server.dori.domain.auth.presentation.dto.response.TokenDto;
import com.server.dori.domain.auth.service.implementation.AuthReader;
import com.server.dori.domain.auth.service.implementation.AuthValidator;
import com.server.dori.domain.auth.service.implementation.TokenIssuer;
import com.server.dori.domain.member.entity.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QueryAuthService {
	private final AuthReader authReader;
	private final AuthValidator authValidator;
	private final TokenIssuer tokenIssuer;

	@Transactional(readOnly = true)
	public TokenDto oauth2Login(OAuth2User oauth2User) {
		if (oauth2User == null) {
			throw AuthNotFoundException.oauthUserNotFound();
		}

		Member member = authReader.findMemberByOAuth2User(oauth2User);
		return tokenIssuer.issueToken(member);
	}

	@Transactional(readOnly = true)
	public TokenDto reissue(String refreshToken) {
		authValidator.validateRefreshToken(refreshToken);
		return tokenIssuer.reissueToken(refreshToken);
	}
}
