package efub.session.blog.comment.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CommentModifyRequestDto {
    @NotNull(message = "내용을 입력해주세요.")
    private String content;
}
