package com.server.dori.domain.auth.service.implementation;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.server.dori.domain.auth.presentation.dto.response.TokenDto;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.service.implementation.MemberReader;
import com.server.dori.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenIssuer {

	private final JwtTokenProvider jwtTokenProvider;
	private final MemberReader memberReader;

	public TokenDto issueToken(Member member) {
		Collection<? extends GrantedAuthority> authorities = java.util.List.of(
			new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + member.getRole().name())
		);
		Authentication authentication = createAuthentication(member.getEmail(), authorities);
		return issueTokenPair(authentication);
	}

	public TokenDto reissueToken(String refreshToken) {
		String email = jwtTokenProvider.getEmailFromToken(refreshToken);

		Member member = memberReader.getMemberByEmail(email);

		return issueToken(member);
	}

	private TokenDto issueTokenPair(Authentication authentication) {
		String accessToken = jwtTokenProvider.createAccessToken(authentication);
		String refreshToken = jwtTokenProvider.createRefreshToken(authentication);
		return TokenDto.of("Bearer", accessToken, refreshToken);
	}

	private Authentication createAuthentication(String email, Collection<? extends GrantedAuthority> authorities) {
		UserDetails userDetails = new User(email, "{noop}oauth2", authorities);
		return new UsernamePasswordAuthenticationToken(userDetails, "{noop}oauth2", authorities);
	}
}
