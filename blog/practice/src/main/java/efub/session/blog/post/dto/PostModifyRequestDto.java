package efub.session.blog.post.dto;

import lombok.Getter;

@Getter
public class PostModifyRequestDto {
    private Long memberId;
    private Boolean anonymous;
    private String content;
}
