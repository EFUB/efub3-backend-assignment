package efub.assignment.community.board.dto;


import lombok.Getter;

import java.time.LocalDateTime;

/*
{
    "title" : "벗들의 벼룩",
    "desc" : "벗들의 중고거래 모임",
    "notice" : "사진을 꼭 올려야 합니다~",
    "ownerId" : 1
}
 */

@Getter
public class BoardCreateRequestDto {
    private String title;
    private String description;
    private String notice;
    private Long ownerId;
}
