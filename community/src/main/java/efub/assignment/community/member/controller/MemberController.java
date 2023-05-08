package efub.assignment.community.member.controller;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.dto.MemberResponseDto;
import efub.assignment.community.member.dto.MemberUpdateRequestDto;
import efub.assignment.community.member.dto.SignUpRequestDto;
import efub.assignment.community.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MemberResponseDto signUp(@RequestBody @Valid SignUpRequestDto requestDto){
        Long id = memberService.signUp(requestDto);
        Member findMember = memberService.findMemberById(id);
        return new MemberResponseDto(findMember);
    }

    @GetMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberResponseDto getMember(@PathVariable Long memberId){
        Member findMember = memberService.findMemberById(memberId);
        return new MemberResponseDto(findMember);
    }

    @PatchMapping("/profile/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberResponseDto update(@PathVariable final Long memberId, @RequestBody @Valid final MemberUpdateRequestDto responseDto){
        Long id = memberService.update(memberId, responseDto);
        Member findMember = memberService.findMemberById(id);
        return new MemberResponseDto(findMember);
    }

    @PatchMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String withdraw(@PathVariable Long memberId){
        memberService.withdraw(memberId);
        return "성공적으로 탈퇴가 완료되었습니다.";
    }
}
