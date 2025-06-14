package com.server.dori.domain.member.entity;

import com.server.dori.domain.member.entity.sub.Grade;
import com.server.dori.domain.member.entity.sub.LearningStyle;
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
	private Grade grade;

	private String currentUniversity;

	private String currentMajor;

	private String targetUniversity;

	private String targetMajor;

	@Enumerated(EnumType.STRING)
	private LearningStyle learningStyle;

	private Integer learningStyleScore;

	public void updateForSignup(String nickname, Grade grade, String currentUniversity, String currentMajor,
		String targetUniversity, String targetMajor,
		LearningStyle learningStyle, Integer learningStyleScore) {
		this.nickname = nickname;
		this.grade = grade;
		this.currentUniversity = currentUniversity;
		this.currentMajor = currentMajor;
		this.targetUniversity = targetUniversity;
		this.targetMajor = targetMajor;
		this.learningStyle = learningStyle;
		this.learningStyleScore = learningStyleScore;
	}

	public void updateInfo(String nickname, Grade grade, String targetUniversity, String targetMajor) {
		this.nickname = nickname;
		this.grade = grade;
		this.targetUniversity = targetUniversity;
		this.targetMajor = targetMajor;
	}

	// TODO - 이미지 매핑
	public String getProfileImageUrl() {
		return null;
	}

	public boolean isMemberInfoCompleted() {
		return grade != null && targetUniversity != null && !targetUniversity.isBlank()
			&& targetMajor != null && !targetMajor.isBlank() && learningStyle != null;
	}
}
