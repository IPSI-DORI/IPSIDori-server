package com.server.dori.domain.course.entity.sub;

import com.server.dori.domain.course.entity.Course;

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
@Table(name = "`lectures`")
public class Lecture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String info;

	private boolean isCheck;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;

	@Builder
	public Lecture(String title, String info, boolean isCheck) {
		this.title = title;
		this.info = info;
		this.isCheck = isCheck;
	}

	public boolean hasCourse() {
		return this.course != null;
	}

	public void assignCourse(Course course) {
		this.course = course;
	}

	public void updateLecture(String title, String info) {
		this.title = title;
		this.info = info;
	}

	public void updateCheck(boolean isCheck) {
		this.isCheck = true;
	}
}
