package com.server.dori.domain.curriculum.entity;

import java.time.LocalDateTime;

import com.server.dori.domain.curriculum.entity.type.Platform;
import com.server.dori.domain.member.entity.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "`curriculums`")
public class Curriculum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String subject;

	private String elective;

	private String studyTime;

	private String studyDays;

	private String weakProblemQuestion;

	private String learningGoalQuestion;

	private LocalDateTime createdAt;

	@Enumerated(EnumType.STRING)
	private Platform platform;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member creator;

	@Builder
	public Curriculum(Member creator, String subject, String elective, String studyTime, String studyDays, String weakProblemQuestion, String learningGoalQuestion, LocalDateTime createdAt, Platform platform) {
		this.creator = creator;
		this.subject = subject;
		this.elective = elective;
		this.studyTime = studyTime;
		this.studyDays = studyDays;
		this.weakProblemQuestion = weakProblemQuestion;
		this.learningGoalQuestion = learningGoalQuestion;
		this.createdAt = LocalDateTime.now();
		this.platform = platform;
	}
}
