package efub.backend.assignment.post.dto;

import lombok.Getter;

/*
{
    "writerId":"1",
    "boardId":"1",
    "title" : "나는 퍼비",
    "content": "오늘 이펍 세션이 있는 날이다. "
}
 */
@Getter
public class PostRequestDto {
    private Long writerId;
    private Long boardId;
    private String title;
    private String content;

}