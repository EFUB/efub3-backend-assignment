package efub.backend.assignment.comment.dto;

import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.comment.domain.Comment;
import efub.backend.assignment.post.domain.Post;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
{
    "writerId":"1",
    "content": "댓글 달아요"
}
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {

    @NotNull(message = "작성자 ID를 입력해주세요.")
    private Long writerId;

    @NotNull(message = "내용을 입력해주세요.")
    private String content;

    public Comment toEntity(Post post, Member writer) {
        return Comment.builder()
                .post(post)
                .writer(writer)
                .content(this.content)
                .build();
    }
}
