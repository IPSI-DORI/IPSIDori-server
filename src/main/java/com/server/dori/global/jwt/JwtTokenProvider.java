package com.server.dori.global.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.server.dori.domain.auth.exception.AuthErrorStatus;
import com.server.dori.domain.auth.exception.AuthException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

	private static final String AUTHORITIES_DELIMITER = ",";
	private static final int MIN_SECRET_KEY_LENGTH = 32;
	private static final String AUTHORITIES_KEY = "auth";

	private final JwtProperties jwtProperties;
	private Key key;

	@PostConstruct
	public void init() {
		String secretKey = jwtProperties.getSecretKey();
		if (secretKey == null || secretKey.getBytes(StandardCharsets.UTF_8).length < MIN_SECRET_KEY_LENGTH) {
			throw new IllegalArgumentException("JWT secret key must be at least 32 characters long");
		}
		byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	public String createAccessToken(Authentication authentication) {
		String authorities = authentication.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(AUTHORITIES_DELIMITER));

		long now = System.currentTimeMillis();
		Date validity = new Date(now + jwtProperties.getAccessTokenValidityInMilliseconds());

		return Jwts.builder()
			.setSubject(authentication.getName())
			.claim(AUTHORITIES_KEY, authorities)
			.setIssuedAt(new Date(now))
			.setExpiration(validity)
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}

	public String createRefreshToken(Authentication authentication) {
		long now = (new Date()).getTime();
		Date validity = new Date(now + jwtProperties.getRefreshTokenValidityInMilliseconds());

		return Jwts.builder()
			.setSubject(authentication.getName())
			.signWith(key, SignatureAlgorithm.HS256)
			.setExpiration(validity)
			.compact();
	}

	public Authentication getAuthentication(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();

			Collection<? extends GrantedAuthority> authorities =
				Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());

			UserDetails principal = new User(claims.getSubject(), "", authorities);
			return new UsernamePasswordAuthenticationToken(principal, token, authorities);
		} catch (ExpiredJwtException e) {
			throw new AuthException(AuthErrorStatus.EXPIRED_TOKEN);
		} catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
			throw new AuthException(AuthErrorStatus.INVALID_TOKEN);
		}
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException e) {
			log.info("만료된 JWT 토큰입니다.");
			throw new AuthException(AuthErrorStatus.EXPIRED_TOKEN);
		} catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
			log.info("유효하지 않은 JWT 토큰입니다.");
			throw new AuthException(AuthErrorStatus.INVALID_TOKEN);
		}
	}
}
