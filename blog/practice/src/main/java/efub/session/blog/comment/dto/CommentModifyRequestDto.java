package efub.session.blog.comment.dto;

import lombok.Getter;

@Getter
public class CommentModifyRequestDto {
    private Long memberId;
    private String content;
}
