package com.efub.community.domain.member.auth.service;

import static org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor.*;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.efub.community.domain.member.auth.dto.response.JwtResponseDto;
import com.efub.community.domain.member.auth.feign.client.KakaoTokenClient;
import com.efub.community.domain.member.auth.feign.client.KakaoUserInfoClient;
import com.efub.community.domain.member.auth.feign.dto.KakaoTokenRequestDto;
import com.efub.community.domain.member.auth.feign.dto.KakaoTokenResponseDto;
import com.efub.community.domain.member.auth.feign.dto.KakaoUserInfoResponseDto;
import com.efub.community.domain.member.domain.Member;
import com.efub.community.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoOauthService {

	private final KakaoTokenClient kakaoTokenClient;
	private final KakaoUserInfoClient kakaoUserInfoClient;
	private final MemberRepository memberRepository;
	private final AuthService authService;
	private final PasswordEncoder passwordEncoder;

	@Value("${kakao.client-id}")
	private String clientId;

	@Value("${kakao.credentials}")
	private String kakaoCredentials;

	@Value("${kakao.redirect-uri}")
	private String redirectUri;

	public JwtResponseDto login(String code) {
		String kakaoToken = getKakaoToken(code);
		String username = getKakaoUsername(kakaoToken);
		log.debug("[KAKAO] 회원 ID: {}", username);
		createMember(username);
		return authService.login(username, kakaoCredentials);
	}

	private String getKakaoToken(String code) {
		KakaoTokenResponseDto tokenResponseDto = kakaoTokenClient.getToken(
			KakaoTokenRequestDto.builder()
				.grant_type("authorization_code")
				.client_id(clientId)
				.redirect_uri(redirectUri)
				.code(code)
				.build()
		);
		return tokenResponseDto.getAccessToken();
	}

	private String getKakaoUsername(String kakaoToken) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.AUTHORIZATION, BEARER + " " + kakaoToken);
		KakaoUserInfoResponseDto kakaoUserInfo = kakaoUserInfoClient.getUserInfo(httpHeaders);
		return String.valueOf(kakaoUserInfo.getId());
	}

	private void createMember(String username) {
		Optional<Member> optionalMember = memberRepository.findByEmail(username);
		if (optionalMember.isEmpty()) {
			Member member = Member.builder()
				.email(username)
				.encodedPassword(passwordEncoder.encode(kakaoCredentials))
				.build();
			memberRepository.save(member);
		}
	}
}
