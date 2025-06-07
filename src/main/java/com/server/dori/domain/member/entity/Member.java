package com.server.dori.domain.member.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.server.dori.domain.member.presentation.dto.ProfileUpdateDto;
import com.server.dori.global.common.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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

	private String socialId;

	@OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private MemberProfile profile;

	@Builder
	public Member(String email, String nickname, Role role, SocialType socialType, String socialId) {
		this.email = email;
		this.nickname = nickname;
		this.role = role;
		this.socialType = socialType;
		this.socialId = socialId;
	}

	public void initializeProfile() {
		this.profile = new MemberProfile();
	}

	public void updateProfile(ProfileUpdateDto profileDto) {
		if (this.profile == null) {
			initializeProfile();
		}

		this.profile.updateProfile(
			profileDto.grade(),
			profileDto.currentUniversity(),
			profileDto.currentMajor(),
			profileDto.targetUniversity(),
			profileDto.targetMajor(),
			LearningStyle.fromScore(profileDto.learningStyleScore()),
			profileDto.learningStyleScore()
		);
	}

	public boolean isProfileCompleted() {
		return profile != null && profile.isProfileCompleted();
	}

	public boolean isRetryStudent() {
		return profile != null && profile.isRetryStudent();
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
