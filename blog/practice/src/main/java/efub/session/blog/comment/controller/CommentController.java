package efub.session.blog.comment.controller;


import efub.session.blog.comment.domain.Comment;
import efub.session.blog.comment.dto.CommentRequestDto;
import efub.session.blog.comment.dto.CommentResponseDto;
import efub.session.blog.comment.service.CommentService;
import efub.session.blog.heart.service.CommentHeartService;
import efub.session.blog.member.dto.MemberInfoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor    // 생성자를 통한 의존관계 주입
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;// 의존관계: CommentController -> CommentService
    private final CommentHeartService commentHeartService;

    // 댓글 수정
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDto modifyPostComment(@PathVariable final Long commentId, @RequestBody @Valid final CommentRequestDto requestDto){
        commentService.modifyComment(requestDto, commentId);
        Comment comment = commentService.findCommentById(commentId);
        return CommentResponseDto.of(comment);
    }

    //댓글 삭제
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String deleteComment(@PathVariable final Long commentId){
        commentService.deleteComment(commentId);
        return "성공적으로 삭제했습니다.";
    }

    //좋아요 추가
    @PostMapping("/hearts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createCommentHeart(@PathVariable final Long commentId, @RequestBody final MemberInfoRequestDto requestDto){
        commentHeartService.create(commentId, requestDto);
        return "좋아요를 눌렀습니다.";
    }

    //좋아요 삭제
    @DeleteMapping("/hearts")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteCommentHeart(@PathVariable final Long commentId, @RequestParam final Long memberId){
        commentHeartService.delete(commentId, memberId);
        return "좋아요가 취소되었습니다.";
    }
}
