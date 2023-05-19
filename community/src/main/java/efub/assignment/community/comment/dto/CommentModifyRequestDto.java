package efub.assignment.community.comment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


/*
{
    "writerId" : "1",
    "content" : "수정된 댓글"
}
 */


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentModifyRequestDto {
    private Long writerId;
    private String content;
}
