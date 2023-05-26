package efub.session.community.account.controller;

import efub.session.community.account.domain.Member;
import efub.session.community.account.dto.MemberResponseDto;
import efub.session.community.account.dto.MemberUpdateRequestDto;
import efub.session.community.account.dto.PostModifyRequestDto;
import efub.session.community.account.dto.SignUpRequestDto;
import efub.session.community.account.service.MemberService;
import efub.session.community.post.dto.PostResponseDto;
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
        return MemberResponseDto.from(findMember);
    }

    @GetMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberResponseDto getMember(@PathVariable Long memberId){
        Member findMember = memberService.findMemberById(memberId);
        return MemberResponseDto.from(findMember);
    }

    @PatchMapping("/profile/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberResponseDto update(@PathVariable final Long memberId, @RequestBody @Valid final MemberUpdateRequestDto responseDto){
        Long id = memberService.update(memberId, responseDto);
        Member findMember = memberService.findMemberById(id);
        return MemberResponseDto.from(findMember);
    }

    @PatchMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String withdraw(@PathVariable Long memberId){
        memberService.withdraw(memberId);
        return "탈퇴되었습니다.";
    }

}

