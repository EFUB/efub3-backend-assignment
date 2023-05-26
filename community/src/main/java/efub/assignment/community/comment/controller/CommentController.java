package efub.assignment.community.comment.controller;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.dto.CommentModifyRequestDto;
import efub.assignment.community.comment.dto.CommentResponseDto;
import efub.assignment.community.comment.service.CommentService;
import efub.assignment.community.heart.service.CommentHeartService;
import efub.assignment.community.member.dto.MemberInfoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments/{commentId}")
public class CommentController {

    private final CommentService commentService;
    private final CommentHeartService commentHeartService;

    // 수정
    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public CommentResponseDto commentModify(@PathVariable Long commentId, @RequestBody @Valid CommentModifyRequestDto requestDto){
        Comment comment = commentService.modifyComment(commentId, requestDto);
        return new CommentResponseDto(comment);
    }

    // 삭제
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.OK)
    public String commentRemove(@PathVariable final Long commentId){
        commentService.removeComment(commentId);
        return "성공적으로 삭제되었습니다.";
    }

    // 좋아요 생성
    @PostMapping("/hearts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String commentHeartCreate(@PathVariable final Long commentId, @RequestBody final MemberInfoRequestDto requestDto){
        commentHeartService.createCommentHeart(commentId, requestDto);
        return "좋아요를 눌렀습니다.";
    }

    // 좋아요 삭제
    @DeleteMapping("/hearts")
    @ResponseStatus(value = HttpStatus.OK)
    public String commentHeartDelete(@PathVariable final Long commentId, @RequestParam final Long memberId){
        commentHeartService.deleteCommentHeart(commentId, memberId);
        return "좋아요가 취소되었습니다.";
    }
}
