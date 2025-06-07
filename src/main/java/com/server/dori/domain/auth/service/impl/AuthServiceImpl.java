package com.server.dori.domain.auth.service.impl;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.auth.exception.AuthErrorStatus;
import com.server.dori.domain.auth.exception.AuthException;
import com.server.dori.domain.auth.presentation.dto.TokenDto;
import com.server.dori.domain.auth.service.AuthService;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.SocialType;
import com.server.dori.domain.member.entity.repository.MemberRepository;
import com.server.dori.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final JwtTokenProvider jwtTokenProvider;
	private final MemberRepository memberRepository;

	@Override
	@Transactional(readOnly = true)
	public TokenDto oauth2Login(OAuth2User oauth2User) {
		if (oauth2User == null) {
			throw new AuthException(AuthErrorStatus.OAUTH_USER_NOT_FOUND);
		}

		String socialId = oauth2User.getAttribute("id").toString();
		Member member = memberRepository.findBySocialTypeAndSocialId(SocialType.KAKAO, socialId)
			.orElseThrow(() -> new AuthException(AuthErrorStatus.OAUTH_SIGNUP_REQUIRED));

		return createToken(member);
	}

	@Override
	@Transactional
	public TokenDto reissue(String refreshToken) {
		if (!jwtTokenProvider.validateToken(refreshToken)) {
			throw new AuthException(AuthErrorStatus.INVALID_TOKEN);
		}

		Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);
		return createToken(authentication);
	}

	@Override
	@Transactional
	public TokenDto createToken(Member member) {
		Authentication authentication = createAuthentication(
			member.getEmail(),
			member.getAuthorities()
		);
		return createToken(authentication);
	}

	private TokenDto createToken(Authentication authentication) {
		String accessToken = jwtTokenProvider.createAccessToken(authentication);
		String refreshToken = jwtTokenProvider.createRefreshToken(authentication);
		return new TokenDto("Bearer", accessToken, refreshToken);
	}

	private Authentication createAuthentication(String email, Collection<? extends GrantedAuthority> authorities) {
		if (email == null || authorities == null) {
			throw new AuthException(AuthErrorStatus.OAUTH_USER_NOT_FOUND);
		}
		UserDetails userDetails = new User(email, "{noop}oauth2", authorities);
		return new UsernamePasswordAuthenticationToken(userDetails, "{noop}oauth2", authorities);
	}
}
