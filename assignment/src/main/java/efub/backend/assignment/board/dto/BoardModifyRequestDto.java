package efub.backend.assignment.board.dto;

import lombok.Getter;

/*
{
    "boardTitle": "벗들의 벼룩",
    "content": "벗들의 중고거래 모임",
    "notice": "수정되었습니다",
    "boardHostId": 2
}
 */
@Getter //requestdto에서 대부분 들어가야함
public class BoardModifyRequestDto {
    private String boardTitle;
    private String content;
    private String notice;
    private Long boardHostId;
}
