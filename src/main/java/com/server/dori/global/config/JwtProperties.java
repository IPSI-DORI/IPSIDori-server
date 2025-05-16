package com.server.dori.global.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
	private String secretKey;
	private long accessTokenValidityInMilliseconds;
	private long refreshTokenValidityInMilliseconds;
}
