package com.server.dori.domain.curriculum.entity;

import java.time.LocalDateTime;

import com.server.dori.domain.curriculum.entity.type.Platform;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	private String question1;

	private String question2;

	private LocalDateTime createdAt;

	@Enumerated(EnumType.STRING)
	private Platform platform;

	@Builder
	public Curriculum(String subject, String elective, String studyTime, String studyDays, String question1, String question2, LocalDateTime createdAt, Platform platform) {
		this.subject = subject;
		this.elective = elective;
		this.studyTime = studyTime;
		this.studyDays = studyDays;
		this.question1 = question1;
		this.question2 = question2;
		this.createdAt = createdAt;
		this.platform = platform;
	}
}
