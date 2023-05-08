package com.example.demo.post.dto;

import com.example.demo.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 게시글 관련 응답메시지에 사용되는 DTO
@Getter
@NoArgsConstructor
public class PostResponseDto {

    private Long postId;

    private Long boardId;

    private Long writerId;

    private Boolean anonymous;

    private String content;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public PostResponseDto (Post post) {
        this.postId = post.getPostId();
        this.boardId = post.getBoard().getBoardId();
        this.writerId = post.getWriter().getMemberId();
        this.anonymous = post.getAnonymous();
        this.content = post.getContent();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
    }

}
