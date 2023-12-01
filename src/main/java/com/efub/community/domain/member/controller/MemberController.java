package com.efub.community.domain.member.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.efub.community.domain.member.auth.dto.response.SignUpResponseDto;
import com.efub.community.domain.member.domain.Member;
import com.efub.community.domain.member.dto.request.MemberUpdateRequestDto;
import com.efub.community.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/{memberId}")
	@ResponseStatus(value = HttpStatus.OK)
	public SignUpResponseDto getMember(@PathVariable Long memberId) {
		Member findMember = memberService.findById(memberId);
		return new SignUpResponseDto(findMember);
	}

	@PatchMapping("/profile/{memberId}")
	@ResponseStatus(value = HttpStatus.OK)
	public SignUpResponseDto update(@PathVariable final Long memberId,
		@RequestBody @Valid final MemberUpdateRequestDto requestDto) {
		Member findMember = memberService.findById(memberId);
		return new SignUpResponseDto(findMember);
	}

	@PatchMapping("/{memberId}")
	@ResponseStatus(value = HttpStatus.OK)
	public String withdraw(@PathVariable final long memberId) {
		memberService.withdraw(memberId);
		return "성공적으로 탈퇴가 완료되었습니다";
	}
}
