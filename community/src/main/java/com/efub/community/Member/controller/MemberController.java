package com.efub.community.Member.controller;

import com.efub.community.Member.domain.RefreshToken;
import com.efub.community.Member.dto.RefreshTokenRequestDto;
import com.efub.community.Member.dto.SignInRequestDto;
import com.efub.community.Member.dto.SignInResponseDto;
import com.efub.community.Member.dto.SignUpRequestDto;
import com.efub.community.Member.service.MemberService;
import com.efub.community.Member.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/auth")
@RequestMapping(path = "/auth", produces = "application/json;charset=UTF-8")
public class MemberController {
    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequestDto requestDto) {
        String responseMessage = memberService.signup(requestDto.getEmail(), requestDto.getPw(), requestDto.getUniv(), requestDto.getNickname(), requestDto.getStudentId());
        return ResponseEntity.ok().body(responseMessage);
    }

    // 로그인
    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signin(@RequestBody SignInRequestDto requestDto) {
        SignInResponseDto responseDto = memberService.signin(requestDto.getEmail(), requestDto.getPw());
        return ResponseEntity.ok().body(responseDto);
    }

    // RefreshToken을 이용해 새 AccessToken을 발급받는 요청
    // 프론트에서 유효한 RefreshToken을 보냈다면, 새 AccessToken 값과 기존 RefreshToken 값을 담은 DTO를 응답
    @PostMapping("/refreshtoken")
    public SignInResponseDto requestRefresh(@RequestBody RefreshTokenRequestDto refreshTokenDto) {
        return memberService.requestRefresh(refreshTokenDto.getRefreshToken());
    }
}
