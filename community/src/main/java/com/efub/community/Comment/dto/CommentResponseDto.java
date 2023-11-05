package com.efub.community.Comment.dto;

import com.efub.community.Comment.domain.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String content;
    private String writerName;
    private Long postId;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment){
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.writerName = comment.getWriter().getNickname();
        this.postId = comment.getPost().getPostId();
        this.createAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }

    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(
                comment.getCommentId(),
                comment.getContent(),
                comment.getWriter().getNickname(),
                comment.getPost().getPostId(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
