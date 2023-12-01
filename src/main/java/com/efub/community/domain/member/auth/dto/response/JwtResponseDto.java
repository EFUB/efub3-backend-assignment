package com.efub.community.domain.member.auth.dto.response;

import static org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor.*;

import lombok.Getter;

@Getter
public class JwtResponseDto {

	private final String type = BEARER;
	private final String accessToken;

	public JwtResponseDto(String accessToken) {
		this.accessToken = accessToken;
	}
}
