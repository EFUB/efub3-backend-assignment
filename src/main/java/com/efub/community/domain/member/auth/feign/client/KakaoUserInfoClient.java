package com.efub.community.domain.member.auth.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.efub.community.domain.member.auth.feign.dto.KakaoUserInfoResponseDto;

@FeignClient(name = "kakaoApi", url = "https://kapi.kakao.com/v1/user/access_token_info")
public interface KakaoUserInfoClient {

	@GetMapping
	KakaoUserInfoResponseDto getUserInfo(@RequestHeader HttpHeaders headers);
}
