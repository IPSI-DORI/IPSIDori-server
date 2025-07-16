package com.server.dori.domain.course.entity;

import java.util.ArrayList;
import java.util.List;

import com.server.dori.domain.course.entity.sub.Lecture;
import com.server.dori.domain.course.service.implementation.CourseValidator;
import com.server.dori.domain.member.entity.Member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	private String title;

	private String description;

	private String subject;

	private String teacher;

	private String grade;

	private String platform;

	private int price;

	private String recommend;

	private int checkCount;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Lecture> lectureList = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member creator;

	@Builder
	public Course(Member creator, String title, String description, String subject, String teacher,
		String grade, String platform, int price, String recommend, int checkCount) {
		this.creator = creator;
		this.title = title;
		this.description = description;
		this.subject = subject;
		this.teacher = teacher;
		this.grade = grade;
		this.platform = platform;
		this.price = price;
		this.recommend = recommend;
		this.checkCount = checkCount;
	}

	public void addLecture(Lecture lecture, CourseValidator validator) {
		validator.validateLectureCourseBinding(lecture);
		lecture.assignCourse(this);
		this.lectureList.add(lecture);
	}

	public boolean isCreator(Long memberId) {
		return this.creator.getId().equals(memberId);
	}

	public void increaseCheckCount() {
		this.checkCount++;
	}

	public void decreaseCheckCount() {
		this.checkCount--;
	}
}