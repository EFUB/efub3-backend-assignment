package efub.assignment.community.board.dto;

import efub.assignment.community.board.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
{
    "boardId": 1,
    "title": "벗들의 벼룩",
    "desc": "벗들의 중고거래 모임",
    "notice": "사진을 꼭 올려야 합니다~",
    "ownerId": 1,
    "created": "2023-03-24 22:00"
}
 */

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long boardId;
    private String title;
    private String description;
    private String notice;
    private Long ownerId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardResponseDto(Board board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.notice = board.getNotice();
        this.ownerId = board.getOwner().getMemberId();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
    }
}
