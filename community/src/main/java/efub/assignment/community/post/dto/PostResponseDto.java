package efub.assignment.community.post.dto;

import efub.assignment.community.board.dto.BoardResponseDto;
import efub.assignment.community.member.dto.MemberResponseDto;
import efub.assignment.community.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
{
    "postId": 4,
    "title" : "나는 퍼비",
    "content" : "오늘 이펍 세션이 있는 날이다. "
    "anonymous" : 1,
    "writerId" : 1,
    "boardId" : 5,
    "createdDate": "2023-02-21T12:50:12.666667",
    "modifiedDate": "2023-02-21T12:50:12.666667"
}
 */

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private Boolean anonymous;
    private MemberResponseDto member;
    private BoardResponseDto board;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostResponseDto (Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.anonymous = post.getIsAnonymous();
        this.member = new MemberResponseDto(post.getWriter());
        this.board = new BoardResponseDto(post.getBoard());
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
    }
}
