package com.server.dori.domain.member.entity;

import com.server.dori.domain.member.entity.sub.Role;
import com.server.dori.domain.member.entity.sub.SocialType;
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
public class Member extends BaseEntity {

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
	private MemberInfo memberInfo;

	@Builder
	public Member(String email, String nickname, Role role, SocialType socialType, String socialId) {
		this.email = email;
		this.nickname = nickname;
		this.role = role;
		this.socialType = socialType;
		this.socialId = socialId;
	}

	public void initializeMemberInfo() {
		this.memberInfo = new MemberInfo();
		this.memberInfo.setMember(this);
	}

	public boolean isMemberInfoCompleted() {
		return memberInfo != null && memberInfo.isMemberInfoCompleted();
	}
}
