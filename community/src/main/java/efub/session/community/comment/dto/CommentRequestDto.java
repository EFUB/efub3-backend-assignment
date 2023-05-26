package efub.session.community.comment.dto;

import efub.session.community.account.domain.Member;
import efub.session.community.comment.domain.Comment;
import efub.session.community.post.domain.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {

    @NotNull(message = "작성자 ID를 입력해주세요.")
    private Long memberId;

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
