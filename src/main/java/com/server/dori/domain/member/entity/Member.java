package com.server.dori.domain.member.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.server.dori.domain.member.presentation.dto.ProfileUpdateDto;
import com.server.dori.global.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String nickname;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SocialType socialType;

	@Column(nullable = true)
	private String socialId;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private Grade grade;

	@Column(nullable = true)
	private String currentUniversity;

	@Column(nullable = true)
	private String currentMajor;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private Subject koreanSubject;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private Subject mathSubject;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private Subject inquirySubject1;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private Subject inquirySubject2;

	@Column(nullable = true)
	private String targetUniversity;

	@Column(nullable = true)
	private String targetMajor;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private LearningStyle learningStyle;

	@Column(nullable = true)
	private Integer learningStyleScore;

	@Builder
	public Member(String email, String nickname, Role role, SocialType socialType, String socialId) {
		this.email = email;
		this.nickname = nickname;
		this.role = role;
		this.socialType = socialType;
		this.socialId = socialId;
	}

	public void updateProfile(ProfileUpdateDto profileDto) {
		this.grade = profileDto.grade();
		this.currentUniversity = profileDto.currentUniversity();
		this.currentMajor = profileDto.currentMajor();
		this.koreanSubject = profileDto.koreanSubject();
		this.mathSubject = profileDto.mathSubject();
		this.inquirySubject1 = profileDto.inquirySubject1();
		this.inquirySubject2 = profileDto.inquirySubject2();
		this.targetUniversity = profileDto.targetUniversity();
		this.targetMajor = profileDto.targetMajor();

		if (profileDto.learningStyleScore() != null) {
			this.learningStyle = LearningStyle.fromScore(profileDto.learningStyleScore());
			this.learningStyleScore = profileDto.learningStyleScore();
		}
	}

	public boolean isProfileCompleted() {
		return grade != null && koreanSubject != null && mathSubject != null && inquirySubject1 != null
			   && inquirySubject2 != null && targetUniversity != null && !targetUniversity.isBlank()
			   && targetMajor != null && !targetMajor.isBlank() && learningStyle != null;
	}

	public boolean isRetryStudent() {
		return grade == Grade.RETRY_1 || grade == Grade.RETRY_2;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	// 소셜 로그인만 사용하므로 null 반환
	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
