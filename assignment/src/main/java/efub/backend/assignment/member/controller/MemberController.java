package efub.backend.assignment.member.controller;

import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.member.dto.MemberResponseDto;
import efub.backend.assignment.member.dto.MemberUpdateRequestDto;
import efub.backend.assignment.member.dto.SignUpRequestDto;
import efub.backend.assignment.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController // Rest 방식의 컨트롤러임을 명시, @controller와 @ResponseBody 기능을 동시에 사용
@RequestMapping("/members") // 특정 URL 패턴에 맞는 컨트롤러인지 명시
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping // HTTP 전송 방식에 따라, 해당 메소드의 방식을 지정하는 경우에 사용
    @ResponseStatus(value = HttpStatus.CREATED) // 반환하는 객체의 HTTP 응답의 상태 코드를 설정
    public MemberResponseDto signUp(@RequestBody @Valid SignUpRequestDto requestDto){

        Member member = memberService.signUp(requestDto);
        // signUp 메서드를 한 번 호출하여 Member 객체를 반환하여 DB에 접근을 1회만 하도록 수정
        // findMemberById 메서드를 사용하지 않고 컨트롤러에서 MemberResponseDto로 변환
        return MemberResponseDto.from(member);
    }

    @GetMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberResponseDto getMember(@PathVariable Long memberId){
        Member findMember = memberService.findMemberById(memberId);
        // signUp 메서드를 한 번 호출하여 Member 객체를 반환하여 DB에 접근을 1회만 하도록 수정
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
        return "성공적으로 탈퇴가 완료되었습니다.";
    }
}
