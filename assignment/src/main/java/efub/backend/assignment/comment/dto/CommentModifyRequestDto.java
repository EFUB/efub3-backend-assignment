package efub.backend.assignment.comment.dto;

import lombok.Getter;

/*
{
    "writerId":"1",
    "content": "댓글 수정해요"
}
 */
@Getter
public class CommentModifyRequestDto {
    private Long writerId;
    private String content;
}