package com.efub.community.Comment.controller;

import com.efub.community.Comment.domain.Comment;
import com.efub.community.Comment.dto.CommentRequestDto;
import com.efub.community.Comment.dto.CommentResponseDto;
import com.efub.community.Comment.dto.CommentUpdateRequestDto;
import com.efub.community.Comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    // 댓글 조회
    @GetMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CommentResponseDto commentFind(@PathVariable final Long commentId){
        Comment comment = commentService.findComment(commentId);
        return new CommentResponseDto(comment);
    }

    // 게시글에 달린 댓글 전체 조회
    @GetMapping("/posts/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<CommentResponseDto> commentListFind(@PathVariable final Long postId){
        List<Comment> commentList = commentService.findCommentList(postId);
        List<CommentResponseDto> responseDtoList = commentList.stream()
                .map(CommentResponseDto::from)
                .collect(Collectors.toList());

        return responseDtoList;
    }

    // 댓글 등록
    @PostMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CommentResponseDto commentCreate(@PathVariable Long postId, @RequestBody @Valid CommentRequestDto requestDto){
        Comment comment = commentService.createComment(postId, requestDto);
        return new CommentResponseDto(comment);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CommentResponseDto commentUpdate(@PathVariable Long commentId, @RequestBody @Valid CommentUpdateRequestDto requestDto){
        Comment comment = commentService.updateComment(commentId, requestDto);
        return new CommentResponseDto(comment);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String commentDelete(@PathVariable final Long commentId){
        commentService.deleteComment(commentId);
        return "성공적으로 삭제되었습니다.";
    }

}
