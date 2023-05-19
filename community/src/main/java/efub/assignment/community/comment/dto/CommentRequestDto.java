package efub.assignment.community.comment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


/*
{
    "writerId" : "1",
    "content" : "댓글2"
}
 */


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {
    private Long writerId;
    private String content;
}
