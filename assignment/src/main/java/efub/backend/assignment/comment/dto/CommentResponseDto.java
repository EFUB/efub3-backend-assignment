package efub.backend.assignment.comment.dto;

import efub.backend.assignment.comment.domain.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/*
{
        "commentId": 1,
        "postId": 1,
        "writerName": "이화1",
        "content": "댓글 달아요",
        "createdDate": "2023-05-13T19:08:34.8369287",
        "modifiedDate": "2023-05-13T19:08:34.8369287"
        }

 */
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponseDto {
    private Long commentId;
    private Long postId;
    private String writerName;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static CommentResponseDto of(Comment comment) {
        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .postId(comment.getPost().getPostId())
                .writerName(comment.getWriter().getNickname())
                .content(comment.getContent())
                .createdDate(comment.getCreatedDate())
                .modifiedDate(comment.getModifiedDate())
                .build();
    }
}