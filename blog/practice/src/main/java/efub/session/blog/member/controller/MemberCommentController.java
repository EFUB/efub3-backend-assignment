package efub.session.blog.member.controller;

import efub.session.blog.comment.service.CommentService;
import efub.session.blog.member.domain.Member;
import efub.session.blog.member.dto.MemberCommentsResponseDto;
import efub.session.blog.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/comments")
public class MemberCommentController {
    private final CommentService commentService;
    private final MemberService memberService;

    //특정 유저의 댓글 목록 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MemberCommentsResponseDto readMemberComments(@PathVariable Long memberId){
        Member writer = memberService.findMemberById(memberId);
        List<Comment> commentList = commentService.findCommentListByWriter(writer);
        return MemberCommentsResponseDto.of(writer.getNickname(),commentList);
    }
}
