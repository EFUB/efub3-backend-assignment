package efub.assignment.community.comment.dto;

import efub.assignment.community.comment.domain.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponseDto {
    private Long commentId;
    private String content;
    private String writerName;
    private Long postId;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public CommentResponseDto(Comment comment){
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.writerName = comment.getWriter().getNickname();
        this.postId = comment.getPost().getPostId();
        this.createDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
    }
}
