package efub.session.blog.comment.controller;


import efub.session.blog.comment.domain.Comment;
import efub.session.blog.comment.dto.CommentModifyRequestDto;
import efub.session.blog.comment.dto.CommentResponseDto;
import efub.session.blog.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor    // 생성자를 통한 의존관계 주입
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;    // 의존관계: CommentController -> CommentService

    // 댓글 수정
    @PutMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CommentResponseDto commentModify(@PathVariable Long commentId, @RequestBody CommentModifyRequestDto requestDto){
        Comment comment = commentService.modifyComment(commentId,requestDto);
        return CommentResponseDto.of(comment);
    }
}
