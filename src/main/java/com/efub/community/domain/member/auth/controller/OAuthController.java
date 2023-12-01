package com.efub.community.domain.member.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.efub.community.domain.member.auth.dto.response.JwtResponseDto;
import com.efub.community.domain.member.auth.service.KakaoOauthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {

	private final KakaoOauthService kakaoOauthService;

	@PostMapping("/kakao")
	@ResponseStatus(HttpStatus.CREATED)
	public JwtResponseDto kakaoSignUp(@RequestBody String code) {
		return kakaoOauthService.login(code);
	}
}
