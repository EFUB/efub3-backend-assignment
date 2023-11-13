package com.efub.community.domain.member.auth.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.efub.community.domain.member.auth.dto.request.SignUpRequestDto;
import com.efub.community.domain.member.domain.Member;
import com.efub.community.domain.member.repository.MemberRepository;
import com.efub.community.domain.member.service.MemberService;
import com.efub.community.global.jwt.JwtAuthenticationProvider;
import com.efub.community.global.jwt.JwtToken;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;
	private final JwtAuthenticationProvider jwtAuthenticationProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final MemberService memberService;

	public Member signUp(SignUpRequestDto requestDto) {
		if (memberService.isExistedStudentNo(requestDto.getStudentNo())) {
			throw new IllegalArgumentException("이미 존재하는 학번입니다. " + requestDto.getStudentNo());
		}
		if (memberService.isExistedNickname(requestDto.getNickname())) {
			throw new IllegalArgumentException("중복된 닉네임이 있습니다. " + requestDto.getNickname());
		}
		String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
		return memberRepository.save(requestDto.toEntity(encodedPassword));
	}

	public JwtToken login(String email, String password) {
		// 이메일 & 패스워드를 기반으로 Authentication 객체 생성
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
		// Authentication 객체 검증
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);
		return jwtAuthenticationProvider.generateJwt(authentication);
	}

}
