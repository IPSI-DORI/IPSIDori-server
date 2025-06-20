package com.server.dori.domain.course.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "`courses`")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long courseId;

	private String title;

	private String description;

	private String subject;

	private String teacher;

	private String grade;

	private String platform;

	private boolean isPaid;

	private int price;

	private String recommend;

	@Builder
	public Course(Long courseId, String title, String description, String subject, String teacher,
		String grade, String platform, boolean isPaid, int price, String recommend) {
		this.courseId = courseId;
		this.title = title;
		this.description = description;
		this.subject = subject;
		this.teacher = teacher;
		this.grade = grade;
		this.platform = platform;
		this.isPaid = isPaid;
		this.price = price;
		this.recommend = recommend;
	}
}