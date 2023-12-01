package com.efub.community.domain.member.auth.feign.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoTokenResponseDto {

	private String token_type;
	private String access_token;
	private int expires_in;
	private String refresh_token;
	private int refresh_token_expires_in;

	public String getAccessToken() {
		return access_token;
	}
}
