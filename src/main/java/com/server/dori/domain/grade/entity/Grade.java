package com.server.dori.domain.grade.entity;

import java.time.LocalDate;

import com.server.dori.domain.grade.presentation.dto.request.GradeRequest;
import com.server.dori.domain.grade.presentation.dto.request.GradeWithCurriculumRequest;
import com.server.dori.domain.member.entity.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "grades")
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String subjects;

	private String elective;

	private String exam;

	private int score;

	private int grade;

	private double percent;

	private LocalDate createdAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member creator;

	@Builder
	public Grade(Member creator, String subjects, String elective, String exam, int score, int grade, double percent, LocalDate createdAt) {
		this.creator = creator;
		this.subjects = subjects;
		this.elective = elective;
		this.exam = exam;
		this.score = score;
		this.grade = grade;
		this.percent = percent;
		this.createdAt = LocalDate.now();
	}

	public void saveGrade(GradeWithCurriculumRequest request) {
		this.exam = request.exam();
		this.score = request.score();
		this.grade = request.grade();
		this.percent = request.percent();
	}

	public void updateGrade(GradeRequest request) {
		this.subjects = request.subjects();
		this.elective = request.elective();
		this.exam = request.exam();
		this.score = request.score();
		this.grade = request.grade();
		this.percent = request.percent();
	}

	public boolean isCreator(Long userId) {
		return this.creator.equals(userId);
	}
}
