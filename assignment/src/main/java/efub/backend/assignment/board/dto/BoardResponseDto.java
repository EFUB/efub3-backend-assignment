package efub.backend.assignment.board.dto;

import efub.backend.assignment.board.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
{
    "boardTitle": "벗들의 벼룩",
    "content": "벗들의 중고거래 모임",
    "notice": "사진을 꼭 올려야합니다!",
    "boardHostId": "user1",
    "boardId": 1,
    "createdDate": "2023/03/24"
}
 */
@Getter
@NoArgsConstructor // 생성자 만들기
public class BoardResponseDto {
    private String boardTitle;
    private String content;
    private String notice;
    private Long boardHostId;
    private Long boardId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardResponseDto(Board board) {
        this.boardTitle = board.getBoardTitle();
        this.content = board.getContent();
        this.notice = board.getNotice();
        this.boardHostId = board.getBoardHost().getMemberId();
        this.boardId = board.getBoardId();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
    }
}