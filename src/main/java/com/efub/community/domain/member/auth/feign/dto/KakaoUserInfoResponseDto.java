package com.efub.community.domain.member.auth.feign.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoUserInfoResponseDto {

	private long id;
	private int expires_in;
	private int app_id;
}
