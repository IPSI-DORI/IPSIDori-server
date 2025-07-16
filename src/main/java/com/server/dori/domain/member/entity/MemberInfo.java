package com.server.dori.domain.member.entity;

import com.server.dori.domain.member.entity.sub.CharacterType;
import com.server.dori.domain.member.entity.sub.LearningStyle;
import com.server.dori.domain.member.entity.sub.SchoolYear;
import com.server.dori.domain.member.exception.MemberInvalidException;
import com.server.dori.global.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member_infos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfo extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	private String nickname;

	@Enumerated(EnumType.STRING)
	private SchoolYear schoolYear;

	private String currentUniversity;

	private String currentMajor;

	private String targetUniversity;

	private String targetMajor;

	@Enumerated(EnumType.STRING)
	private LearningStyle learningStyle;

	private int learningStyleScore;

	/**
	 * 회원가입 시 정보 업데이트
	 */
	public void updateForSignup(String nickname, SchoolYear schoolYear, String currentUniversity, String currentMajor,
		String targetUniversity, String targetMajor,
		LearningStyle learningStyle, int learningStyleScore) {

		validateLearningStyleScore(learningStyleScore);
		validateRequiredFields(nickname, schoolYear, targetUniversity, targetMajor, learningStyle);

		this.nickname = nickname;
		this.schoolYear = schoolYear;
		this.currentUniversity = currentUniversity;
		this.currentMajor = currentMajor;
		this.targetUniversity = targetUniversity;
		this.targetMajor = targetMajor;
		this.learningStyle = learningStyle;
		this.learningStyleScore = learningStyleScore;
	}

	/**
	 * 회원 정보 수정
	 */
	public void updateInfo(String nickname, SchoolYear schoolYear, String targetUniversity, String targetMajor) {
		validateRequiredFields(nickname, schoolYear, targetUniversity, targetMajor, this.learningStyle);

		this.nickname = nickname;
		this.schoolYear = schoolYear;
		this.targetUniversity = targetUniversity;
		this.targetMajor = targetMajor;
	}

	/**
	 * 회원 정보 완료 여부 확인
	 */
	public boolean isMemberInfoCompleted() {
		return schoolYear != null && targetUniversity != null && !targetUniversity.isBlank()
			&& targetMajor != null && !targetMajor.isBlank() && learningStyle != null;
	}

	/**
	 * 도메인 검증: 학습 스타일 점수 유효성
	 */
	private void validateLearningStyleScore(int score) {
		if (!CharacterType.isValidScore(score)) {
			throw MemberInvalidException.invalidCharacterScore();
		}
	}

	/**
	 * 도메인 검증: 필수 필드 유효성
	 */
	private void validateRequiredFields(String nickname, SchoolYear schoolYear,
		String targetUniversity, String targetMajor, LearningStyle learningStyle) {

		if (nickname == null || nickname.isBlank()) {
			throw MemberInvalidException.nicknameRequired();
		}
		if (schoolYear == null) {
			throw MemberInvalidException.schoolYearRequired();
		}
		if (targetUniversity == null || targetUniversity.isBlank()) {
			throw MemberInvalidException.targetUniversityRequired();
		}
		if (targetMajor == null || targetMajor.isBlank()) {
			throw MemberInvalidException.targetMajorRequired();
		}
		if (learningStyle == null) {
			throw MemberInvalidException.learningStyleRequired();
		}
	}
}
