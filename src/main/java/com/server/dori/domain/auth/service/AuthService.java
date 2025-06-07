package com.server.dori.domain.auth.service;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.server.dori.domain.auth.presentation.dto.TokenDto;
import com.server.dori.domain.member.entity.Member;

public interface AuthService {
	TokenDto oauth2Login(OAuth2User oauth2User);

	TokenDto reissue(String refreshToken);

	TokenDto createToken(Member member);
}
