package efub.backend.assignment.comment.controller;

import efub.backend.assignment.comment.domain.Comment;
import efub.backend.assignment.comment.dto.CommentModifyRequestDto;
import efub.backend.assignment.comment.dto.CommentResponseDto;
import efub.backend.assignment.comment.service.CommentService;
import efub.backend.assignment.heart.service.CommentHeartService;
import efub.backend.assignment.member.dto.MemberInfoRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor    // 생성자를 통한 의존관계 주입
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final CommentHeartService commentHeartService;// 의존관계: CommentController -> CommentService

    @PutMapping("/{commentId}") // 댓글 수정
    @ResponseStatus(value = HttpStatus.OK)
    public CommentResponseDto commentModify(@PathVariable Long commentId, @RequestBody @Valid CommentModifyRequestDto requestDto) {
        Comment comment = commentService.modifyComment(commentId, requestDto);
        return CommentResponseDto.of(comment);
    }

    @DeleteMapping("/{commentId}") // 댓글 삭제
    @ResponseStatus(HttpStatus.OK)
    public String deleteComment(@PathVariable final Long commentId){
        commentService.deleteComment(commentId);
        return "성공적으로 삭제했습니다.";
    }

    // 댓글 좋아요 추가
    @PostMapping("/hearts/{commentId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createCommentHeart(@PathVariable final Long commentId, @RequestBody final MemberInfoRequestDto requestDto){
        commentHeartService.create(commentId, requestDto);
        return "좋아요를 눌렀습니다.";
    }

    // 댓글 좋아요 삭제
    @DeleteMapping("/hearts/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteCommentHeart(@PathVariable final Long commentId, @RequestParam final Long memberId){
        commentHeartService.delete(commentId, memberId);
        return "좋아요가 취소되었습니다.";
    }
}