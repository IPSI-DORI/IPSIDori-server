package com.server.dori.domain.member.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.server.dori.domain.member.service.implementation.MemberReader;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final MemberReader memberReader;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return memberReader.loadUserByUsername(username);
	}
}
