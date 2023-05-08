package efub.assignment.community.board.dto;

import efub.assignment.community.board.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long boardId;
    private String masterName;
    private String boardName;
    private String description;
    private String notice;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardResponseDto(Board board) {
        this.boardId = board.getBoardId();
        this.masterName = board.getMaster().getNickname();
        this.boardName = board.getBoardName();
        this.description = board.getDescription();
        this.notice = board.getNotice();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
    }
}
