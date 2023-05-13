package efub.backend.assignment.member.controller;

// url: /members/{memberId}/comments

import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.member.dto.MemberCommentsResponseDto;
import efub.backend.assignment.member.service.MemberService;
import efub.backend.assignment.comment.domain.Comment;
import efub.backend.assignment.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members/{memberId}/comments")
@RequiredArgsConstructor
public class MemberCommentController {

    // 의존관계 : AccountCommentController -> CommentService
    private final CommentService commentService;
    // 의존관계 : AccountCommentController -> AccountService
    private final MemberService memberService;

    // 특정 유저의 댓글 목록 조회
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public MemberCommentsResponseDto readMemberComments(@PathVariable Long memberId) {
        Member writer = memberService.findMemberById(memberId);
        List<Comment> commentList = commentService.findCommentListByWriter(writer);
        return MemberCommentsResponseDto.of(writer.getNickname(), commentList);
    }

}
