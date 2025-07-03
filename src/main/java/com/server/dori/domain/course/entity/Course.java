package com.server.dori.domain.course.entity;

import java.util.ArrayList;
import java.util.List;

import com.server.dori.domain.course.entity.sub.Lecture;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	private Long id;

	private String externalCourseId;

	private String title;

	private String description;

	private String subject;

	private String teacher;

	private String grade;

	private String platform;

	private int price;

	private String recommend;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Lecture> lectureList = new ArrayList<>();

	@Builder
	public Course(Long id, String externalCourseId, String title, String description, String subject, String teacher,
		String grade, String platform, int price, String recommend) {
		this.id = id;
		this.externalCourseId = externalCourseId;
		this.title = title;
		this.description = description;
		this.subject = subject;
		this.teacher = teacher;
		this.grade = grade;
		this.platform = platform;
		this.price = price;
		this.recommend = recommend;
	}
}