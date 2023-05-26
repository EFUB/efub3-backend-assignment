package efub.backend.assignment.post.dto;

import efub.backend.assignment.post.domain.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
{
    "postId": 4,
    "writerName": "퍼비",
    "title": "나는 퍼비",
    "content": "오늘 이펍 세션이 있는 날이다. ",
    "createdDate": "2023-02-21T12:50:12.666667",
    "modifiedDate": "2023-02-21T12:50:12.666667"
}
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostResponseDto {
    private Long postId;
    private String writerName;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostResponseDto(Long postId, String writerName, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.postId = postId;
        this.writerName = writerName;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static PostResponseDto from(Post post) {
        return new PostResponseDto(
                post.getPostId(),
                post.getWriter().getNickname(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedDate(),
                post.getModifiedDate());
    }
}