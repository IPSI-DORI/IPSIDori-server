package com.server.dori.domain.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dori.domain.auth.presentation.dto.request.KakaoLoginRequest;
import com.server.dori.domain.auth.presentation.dto.response.KakaoLoginResponse;
import com.server.dori.domain.auth.presentation.dto.response.TokenDto;
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

	public record KakaoLoginResult(
		TokenDto tokenDto,
		KakaoLoginResponse loginResponse
	) {
	}

	public KakaoLoginResult kakaoLogin(KakaoLoginRequest request) {
		// 1. 인가코드로 액세스 토큰 발급
		String accessToken = kakaoApiService.getAccessTokenFromAuthorizationCode(request.authorizationCode());

		// 2. 액세스 토큰으로 사용자 정보 조회
		KakaoUserInfo userInfo = kakaoApiService.getUserInfo(accessToken);

		// 3. 회원 조회 또는 생성
		Member member = authReader.findMemberByKakaoUserInfo(userInfo)
			.orElseGet(() -> authCreator.createMemberByKakaoUserInfo(userInfo));

		// 4. JWT 토큰 발급
		TokenDto tokenDto = tokenIssuer.issueToken(member);

		// 5. 회원가입 완료 여부 확인
		KakaoLoginResponse loginResponse =
			member.getMemberInfo() != null && member.getMemberInfo().isMemberInfoCompleted()
				? KakaoLoginResponse.loginComplete()
				: KakaoLoginResponse.signupRequired();

		return new KakaoLoginResult(tokenDto, loginResponse);
	}
}
