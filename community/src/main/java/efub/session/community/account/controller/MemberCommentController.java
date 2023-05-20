package efub.session.community.account.controller;

import efub.session.community.account.domain.Member;
import efub.session.community.account.dto.MemberCommentsResponseDto;
import efub.session.community.account.service.MemberService;
import efub.session.community.comment.domain.Comment;
import efub.session.community.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts/{accountId}/comments")
@RequiredArgsConstructor
public class MemberCommentController {

    // 의존관계 : AccountCommentController -> CommentService
    public final CommentService commentService;
    // 의존관계 : AccountCommentController -> AccountService
    private final MemberService memberService;

    // 특정 유저의 댓글 목록 조회
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public MemberCommentsResponseDto readAccountComments(@PathVariable Long accountId) {
        Member member = memberService.findMemberById(accountId);
        List<Comment> commentList = commentService.findCommentListByWriter(member);
        return MemberCommentsResponseDto.of(member.getNickname(), commentList);
    }

}
