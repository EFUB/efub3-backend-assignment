package efub.assignment.community.post.dto;


import lombok.Getter;

/*
{
    "title" : "나는 퍼비",
    "content" : "오늘 이펍 세션이 있는 날이다."
}
 */
@Getter
public class PostModifyRequestDto {
    private String title;
    private String content;
}
