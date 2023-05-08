package efub.assignment.community.post.dto;

import lombok.Getter;


/*
{
    "title" : "나는 퍼비",
    "content" : "오늘 이펍 세션이 있는 날이다. ",
    "anonymous" : 1,
    "writerId" : "1",
    "boardId" : "5"
}
 */
@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private Boolean anonymous;
    private Long writerId;
    private Long boardId;
}
