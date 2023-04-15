package efub.backend.assignment.post.dto;


import efub.backend.assignment.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/*
{
    "postId": 4,
    "boardId" : 1,
    "writerId": 1,
    "writerName": "퍼비",
    "title": "나는 퍼비",
    "content": "오늘 이펍 세션이 있는 날이다. ",
    "createdDate": "2023-02-21T12:50:12.666667",
    "modifiedDate": "2023-02-21T12:50:12.666667"
}
 */
@Getter
@NoArgsConstructor // 생성자 만들기
@Transactional
public class PostResponseDto {
    private Long postId;
    private Long writerId;
    private Long boardId;
    private String writerName;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostResponseDto(Post post) {
        this.postId = post.getPostId();
        this.writerId = post.getWriter().getMemberId();
        this.boardId = post.getBoard().getBoardId();
        this.writerName = post.getWriter().getNickname();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
    }
}