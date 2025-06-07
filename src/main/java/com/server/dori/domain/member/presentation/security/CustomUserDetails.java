package com.server.dori.domain.member.presentation.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.server.dori.domain.member.entity.Member;

import lombok.Getter;

@Getter
public class CustomUserDetails implements UserDetails {
	private final Long memberId;
	private final String email;
	private final Collection<? extends GrantedAuthority> authorities;

	public CustomUserDetails(Member member) {
		this.memberId = member.getId();
		this.email = member.getEmail();
		this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + member.getRole().name()));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	// 소셜 로그인만 사용하므로 null 반환
	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return email;
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
