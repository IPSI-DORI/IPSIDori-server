package com.server.dori.member.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.MemberInfo;
import com.server.dori.domain.member.entity.sub.LearningStyle;
import com.server.dori.domain.member.entity.sub.Role;
import com.server.dori.domain.member.entity.sub.SchoolYear;
import com.server.dori.domain.member.entity.sub.SocialType;
import com.server.dori.domain.member.exception.MemberInvalidException;

class MemberInfoTest {

	@DisplayName("회원가입 시 정보가 정상적으로 업데이트된다.")
	@Test
	void testUpdateForSignup() {
		// given
		Member member = createMember();
		member.initializeMemberInfo();
		MemberInfo memberInfo = member.getMemberInfo();

		String nickname = "홍길동";
		SchoolYear schoolYear = SchoolYear.HIGH_1;
		String currentUniversity = "해당없음";
		String currentMajor = "해당없음";
		String targetUniversity = "서울대학교";
		String targetMajor = "컴퓨터공학과";
		LearningStyle learningStyle = LearningStyle.CONCEPTUAL;
		int learningStyleScore = 8;

		// when
		memberInfo.updateForSignup(nickname, schoolYear, currentUniversity, currentMajor,
			targetUniversity, targetMajor, learningStyle, learningStyleScore);

		// then
		assertThat(memberInfo.getNickname()).isEqualTo(nickname);
		assertThat(memberInfo.getSchoolYear()).isEqualTo(schoolYear);
		assertThat(memberInfo.getCurrentUniversity()).isEqualTo(currentUniversity);
		assertThat(memberInfo.getCurrentMajor()).isEqualTo(currentMajor);
		assertThat(memberInfo.getTargetUniversity()).isEqualTo(targetUniversity);
		assertThat(memberInfo.getTargetMajor()).isEqualTo(targetMajor);
		assertThat(memberInfo.getLearningStyle()).isEqualTo(learningStyle);
		assertThat(memberInfo.getLearningStyleScore()).isEqualTo(learningStyleScore);
	}

	@DisplayName("회원 정보 수정이 정상적으로 동작한다.")
	@Test
	void testUpdateInfo() {
		// given
		Member member = createMember();
		member.initializeMemberInfo();
		MemberInfo memberInfo = member.getMemberInfo();

		// 초기 데이터 설정
		memberInfo.updateForSignup("초기닉네임", SchoolYear.HIGH_1, "해당없음", "해당없음",
			"초기대학", "초기전공", LearningStyle.CONCEPTUAL, 8);

		String newNickname = "수정된닉네임";
		SchoolYear newSchoolYear = SchoolYear.HIGH_2;
		String newTargetUniversity = "연세대학교";
		String newTargetMajor = "경영학과";

		// when
		memberInfo.updateInfo(newNickname, newSchoolYear, newTargetUniversity, newTargetMajor);

		// then
		assertThat(memberInfo.getNickname()).isEqualTo(newNickname);
		assertThat(memberInfo.getSchoolYear()).isEqualTo(newSchoolYear);
		assertThat(memberInfo.getTargetUniversity()).isEqualTo(newTargetUniversity);
		assertThat(memberInfo.getTargetMajor()).isEqualTo(newTargetMajor);
		// 기존 학습 스타일은 유지되어야 함
		assertThat(memberInfo.getLearningStyle()).isEqualTo(LearningStyle.CONCEPTUAL);
	}

	@DisplayName("회원 정보 완료 여부를 정확히 판단한다.")
	@Test
	void testIsMemberInfoCompleted() {
		// given
		Member member = createMember();
		member.initializeMemberInfo();
		MemberInfo memberInfo = member.getMemberInfo();

		// when & then - 초기 상태에서는 완료되지 않음
		assertThat(memberInfo.isMemberInfoCompleted()).isFalse();

		// when - 필수 정보 입력
		memberInfo.updateForSignup("홍길동", SchoolYear.HIGH_1, "해당없음", "해당없음",
			"서울대학교", "컴퓨터공학과", LearningStyle.CONCEPTUAL, 8);

		// then - 완료 상태
		assertThat(memberInfo.isMemberInfoCompleted()).isTrue();
	}

	@DisplayName("닉네임이 null이면 예외가 발생한다.")
	@Test
	void testUpdateForSignup_NicknameNull() {
		// given
		Member member = createMember();
		member.initializeMemberInfo();
		MemberInfo memberInfo = member.getMemberInfo();

		// when & then
		assertThatThrownBy(() ->
			memberInfo.updateForSignup(null, SchoolYear.HIGH_1, "해당없음", "해당없음",
				"서울대학교", "컴퓨터공학과", LearningStyle.CONCEPTUAL, 8))
			.isInstanceOf(MemberInvalidException.class)
			.hasMessage("닉네임은 필수입니다.");
	}

	@DisplayName("닉네임이 빈 문자열이면 예외가 발생한다.")
	@Test
	void testUpdateForSignup_NicknameBlank() {
		// given
		Member member = createMember();
		member.initializeMemberInfo();
		MemberInfo memberInfo = member.getMemberInfo();

		// when & then
		assertThatThrownBy(() ->
			memberInfo.updateForSignup("", SchoolYear.HIGH_1, "해당없음", "해당없음",
				"서울대학교", "컴퓨터공학과", LearningStyle.CONCEPTUAL, 8))
			.isInstanceOf(MemberInvalidException.class)
			.hasMessage("닉네임은 필수입니다.");
	}

	@DisplayName("학년이 null이면 예외가 발생한다.")
	@Test
	void testUpdateForSignup_SchoolYearNull() {
		// given
		Member member = createMember();
		member.initializeMemberInfo();
		MemberInfo memberInfo = member.getMemberInfo();

		// when & then
		assertThatThrownBy(() ->
			memberInfo.updateForSignup("홍길동", null, "해당없음", "해당없음",
				"서울대학교", "컴퓨터공학과", LearningStyle.CONCEPTUAL, 8))
			.isInstanceOf(MemberInvalidException.class)
			.hasMessage("학년은 필수입니다.");
	}

	@DisplayName("목표 대학교가 null이면 예외가 발생한다.")
	@Test
	void testUpdateForSignup_TargetUniversityNull() {
		// given
		Member member = createMember();
		member.initializeMemberInfo();
		MemberInfo memberInfo = member.getMemberInfo();

		// when & then
		assertThatThrownBy(() ->
			memberInfo.updateForSignup("홍길동", SchoolYear.HIGH_1, "해당없음", "해당없음",
				null, "컴퓨터공학과", LearningStyle.CONCEPTUAL, 8))
			.isInstanceOf(MemberInvalidException.class)
			.hasMessage("목표 대학교는 필수입니다.");
	}

	@DisplayName("목표 전공이 빈 문자열이면 예외가 발생한다.")
	@Test
	void testUpdateForSignup_TargetMajorBlank() {
		// given
		Member member = createMember();
		member.initializeMemberInfo();
		MemberInfo memberInfo = member.getMemberInfo();

		// when & then
		assertThatThrownBy(() ->
			memberInfo.updateForSignup("홍길동", SchoolYear.HIGH_1, "해당없음", "해당없음",
				"서울대학교", "", LearningStyle.CONCEPTUAL, 8))
			.isInstanceOf(MemberInvalidException.class)
			.hasMessage("목표 전공은 필수입니다.");
	}

	@DisplayName("학습 스타일이 null이면 예외가 발생한다.")
	@Test
	void testUpdateForSignup_LearningStyleNull() {
		// given
		Member member = createMember();
		member.initializeMemberInfo();
		MemberInfo memberInfo = member.getMemberInfo();

		// when & then
		assertThatThrownBy(() ->
			memberInfo.updateForSignup("홍길동", SchoolYear.HIGH_1, "해당없음", "해당없음",
				"서울대학교", "컴퓨터공학과", null, 8))
			.isInstanceOf(MemberInvalidException.class)
			.hasMessage("학습 스타일은 필수입니다.");
	}

	@DisplayName("학습 스타일 점수가 유효하지 않으면 예외가 발생한다.")
	@Test
	void testUpdateForSignup_InvalidLearningStyleScore() {
		// given
		Member member = createMember();
		member.initializeMemberInfo();
		MemberInfo memberInfo = member.getMemberInfo();

		// when & then
		assertThatThrownBy(() ->
			memberInfo.updateForSignup("홍길동", SchoolYear.HIGH_1, "해당없음", "해당없음",
				"서울대학교", "컴퓨터공학과", LearningStyle.CONCEPTUAL, 3)) // 유효 범위: 4-16
			.isInstanceOf(MemberInvalidException.class)
			.hasMessage("캐릭터 점수가 유효하지 않습니다. 4점 이상 16점 이하의 값이어야 합니다.");
	}

	private Member createMember() {
		return Member.builder()
			.email("test@example.com")
			.role(Role.USER)
			.socialType(SocialType.KAKAO)
			.build();
	}
}
