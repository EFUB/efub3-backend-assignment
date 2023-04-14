package com.example.demo.member.controller;

import com.example.demo.member.domain.Member;
import com.example.demo.member.dto.MemberResponseDto;
import com.example.demo.member.dto.MemberUpdateRequestDto;
import com.example.demo.member.dto.SignUpRequestDto;
import com.example.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원가입 (POST)
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED) // 성공 시 보낼 HTTP Status code
    // Body로 Dto 데이터를 받아옴
    public MemberResponseDto signUp(@RequestBody @Valid final SignUpRequestDto requestDto) {
        Long id = memberService.signUp(requestDto); // Dto에 들어있는 데이터로 회원가입, 가입 완료된 회원의 아이디 받아옴
        Member findMember = memberService.findMemberById(id);   // 방금 가입 완료된 회원의 아이디로 회원을 조회
        return MemberResponseDto.from(findMember);  // 조회한 회원의 정보를 Dto 객체로 만들어 리턴
    }

    // 멤버 1명 조회 (GET)
    @GetMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    // URL의 path variable로 memberId를 받아옴
    public MemberResponseDto getMember(@PathVariable Long memberId) {
        Member findMember = memberService.findMemberById(memberId);
        return MemberResponseDto.from(findMember);
    }

    // 회원정보(닉네임) 수정 (PATCH)
    @PatchMapping("profile/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    // URL의 path variable로 memberId를 받아오고, Body로 Dto 데이터를 받아옴
    public MemberResponseDto update(@PathVariable final Long memberId, @RequestBody @Valid final MemberUpdateRequestDto requestDto) {
        Long id = memberService.update(memberId, requestDto);
        Member findMember = memberService.findMemberById(id);
        return MemberResponseDto.from(findMember);
    }


    // 회원탈퇴 (PATCH)
    @PatchMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String withdraw(@PathVariable Long memberId) {
        memberService.withdraw(memberId);
        return "성공적으로 탈퇴가 완료되었습니다.";
    }
}
