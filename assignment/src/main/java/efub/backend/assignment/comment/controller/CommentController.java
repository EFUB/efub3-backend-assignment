package efub.backend.assignment.comment.controller;

import efub.backend.assignment.comment.domain.Comment;
import efub.backend.assignment.comment.dto.CommentModifyRequestDto;
import efub.backend.assignment.comment.dto.CommentResponseDto;
import efub.backend.assignment.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor    // 생성자를 통한 의존관계 주입
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;    // 의존관계: CommentController -> CommentService

    @PutMapping("/{commentId}") // 댓글 수정
    @ResponseStatus(value = HttpStatus.OK)
    public CommentResponseDto commentModify(@PathVariable Long commentId, @RequestBody @Valid CommentModifyRequestDto requestDto) {
        Comment comment = commentService.modifyComment(commentId, requestDto);
        return CommentResponseDto.of(comment);
    }
}