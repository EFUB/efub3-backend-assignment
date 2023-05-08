package efub.assignment.community.board.dto;

import lombok.Getter;

/*
{
    "title" : "벗들의 벼룩",
    "desc" : "벗들의 중고거래 모임",
    "notice" : "게시판지기가 변경되었습니다.",
    "ownerId" : 2
}
 */
@Getter
public class BoardUpdateRequestDto {
    private String title;
    private String description;
    private String notice;
    private Long ownerId;
}
