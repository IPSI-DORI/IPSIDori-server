package com.server.dori.domain.member.entity;

import com.server.dori.global.common.BaseEntity;

import jakarta.persistence.Column;
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

@Entity
@Table(name = "member_profiles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProfile extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@Enumerated(EnumType.STRING)
	private Grade grade;

	private String currentUniversity;

	private String currentMajor;

	private String targetUniversity;

	private String targetMajor;

	@Enumerated(EnumType.STRING)
	private LearningStyle learningStyle;

	private Integer learningStyleScore;

	private String profileImageUrl;

	public void updateProfile(Grade grade, String currentUniversity, String currentMajor,
		String targetUniversity, String targetMajor,
		LearningStyle learningStyle, Integer learningStyleScore) {
		this.grade = grade;
		this.currentUniversity = currentUniversity;
		this.currentMajor = currentMajor;
		this.targetUniversity = targetUniversity;
		this.targetMajor = targetMajor;
		this.learningStyle = learningStyle;
		this.learningStyleScore = learningStyleScore;
	}

	public void updateProfileImage(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public boolean isProfileCompleted() {
		return grade != null && targetUniversity != null && !targetUniversity.isBlank()
			&& targetMajor != null && !targetMajor.isBlank() && learningStyle != null;
	}

	public boolean isRetryStudent() {
		return grade == Grade.RETRY_1 || grade == Grade.RETRY_2;
	}
}
