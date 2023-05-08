package efub.assignment.community.post.dto;

import efub.assignment.community.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String boardName;
    private String writerName;
    private Boolean isPrivate;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostResponseDto(Post post) {
        this.postId = post.getPostId();
        this.boardName = post.getBoard().getBoardName();
        this.writerName = post.getWriter().getNickname();
        this.isPrivate = post.getIsPrivate();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
    }
}
