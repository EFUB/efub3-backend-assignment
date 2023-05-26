package efub.assignment.community.post.dto;

import efub.assignment.community.post.domain.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long postId;
    private Long boardId;
    private String title;
    private String content;
    private Long writerId;
    private Boolean isPrivate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostResponseDto(Long postId, Long boardId, String title, String content,
                           Long writerId, Boolean isPrivate, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.postId = postId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writerId = writerId;
        this.isPrivate = isPrivate;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static PostResponseDto from(Post post) {
        return new PostResponseDto(
                post.getPostId(),
                post.getBoard().getBoardId(),
                post.getTitle(),
                post.getContent(),
                post.getWriter().getMemberId(),
                post.getIsPrivate(),
                post.getCreatedDate(),
                post.getModifiedDate());
    }
}
