package com.server.dori.domain.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.auth.presentation.dto.request.KakaoLoginRequest;
import com.server.dori.domain.auth.presentation.dto.response.KakaoLoginResponse;
import com.server.dori.domain.auth.presentation.dto.response.TokenDto;
import com.server.dori.domain.auth.service.dto.KakaoLoginResult;
import com.server.dori.domain.auth.service.dto.KakaoUserInfo;
import com.server.dori.domain.auth.service.implementation.AuthCreator;
import com.server.dori.domain.auth.service.implementation.AuthReader;
import com.server.dori.domain.auth.service.implementation.KakaoApiService;
import com.server.dori.domain.auth.service.implementation.TokenIssuer;
import com.server.dori.domain.member.entity.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandAuthService {

	private final AuthReader authReader;
	private final TokenIssuer tokenIssuer;
	private final KakaoApiService kakaoApiService;
	private final AuthCreator authCreator;

	public KakaoLoginResult kakaoLogin(KakaoLoginRequest request) {
		KakaoUserInfo userInfo = kakaoApiService.getUserInfo(request.accessToken());

		Member member = authReader.findMemberByKakaoUserInfo(userInfo)
			.orElseGet(() -> authCreator.createMemberByKakaoUserInfo(userInfo));

		TokenDto tokenDto = tokenIssuer.issueToken(member);

		KakaoLoginResponse loginResponse =
			member.getMemberInfo() != null && member.getMemberInfo().isMemberInfoCompleted()
				? KakaoLoginResponse.loginComplete()
				: KakaoLoginResponse.signupRequired();

		return new KakaoLoginResult(tokenDto, loginResponse);
	}
}
