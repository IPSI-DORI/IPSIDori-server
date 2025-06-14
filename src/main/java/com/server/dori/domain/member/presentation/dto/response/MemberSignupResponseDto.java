package com.server.dori.domain.member.presentation.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "회원가입 응답 DTO")
public record MemberSignupResponseDto(
	@Schema(description = "회원 ID", example = "1")
	Long memberId,

	@Schema(description = "이메일", example = "user@example.com")
	String email,

	@Schema(description = "사용자 닉네임", example = "홍길동")
	String nickname,

	@Schema(description = "캐릭터 정보")
	CharacterInfo characterInfo
) {
	@Schema(description = "캐릭터 정보")
	public record CharacterInfo(
		@Schema(description = "캐릭터 이름", example = "도리")
		String name,

		@Schema(description = "캐릭터 특징 목록", example = "[\"인강으로 배우는 게 제일 잘 맞는 타입이에요.\", \"강의 흐름을 따라가며 이해하고, 필요한 부분은 여러 번 돌려봐요.\", \"듣고, 정리하고, 다시 듣기! 체계적인 복습 루틴이 있어요.\"]")
		List<String> characteristics,

		@Schema(description = "캐릭터 이미지 URL", example = "https://xxx/xxx.png")
		String imageUrl
	) {
	}
}
