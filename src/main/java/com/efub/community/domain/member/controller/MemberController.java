package com.efub.community.domain.member.controller;

import com.efub.community.domain.member.domain.Member;
import com.efub.community.domain.member.dto.request.MemberUpdateRequestDto;
import com.efub.community.domain.member.dto.response.MemberResponseDto;
import com.efub.community.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

	@GetMapping("/{memberId}")
	@ResponseStatus(value = HttpStatus.OK)
	public MemberResponseDto getMember(@PathVariable Long memberId)
	{
		Member findMember = memberService.findById(memberId);
		return new MemberResponseDto(findMember);
	}


	@PatchMapping("/profile/{memberId}")
	@ResponseStatus(value = HttpStatus.OK)
	public MemberResponseDto update(@PathVariable final Long memberId, @RequestBody @Valid final MemberUpdateRequestDto requestDto) {
		Long id = memberService.update(memberId,requestDto);
		Member findMember = memberService.findById(memberId);
		return new MemberResponseDto(findMember);
	}

}
