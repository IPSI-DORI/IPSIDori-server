package com.server.dori.domain.grade.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grades")
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	private String subjects;

	private String elective;

	private String exam;

	private String score;

	private String grade;

	private String percent;

	private LocalDate createdAt;

	@Builder
	public Grade(String subjects, String elective, String exam, String score, String grade, String percent, LocalDate createdAt) {
		this.subjects = subjects;
		this.elective = elective;
		this.exam = exam;
		this.score = score;
		this.grade = grade;
		this.percent = percent;
		this.createdAt = LocalDate.now();
	}
}
