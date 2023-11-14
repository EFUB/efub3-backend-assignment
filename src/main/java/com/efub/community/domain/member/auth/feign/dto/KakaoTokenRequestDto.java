package com.efub.community.domain.member.auth.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class KakaoTokenRequestDto {

	private String grant_type;
	private String client_id;
	private String redirect_uri;
	private String code;
}
