package efub.assignment.community.board.dto;

import lombok.Getter;

@Getter
public class BoardModifyRequestDto {
    private String boardName;
    private String description;
    private String notice;
    private Long masterId;
}
