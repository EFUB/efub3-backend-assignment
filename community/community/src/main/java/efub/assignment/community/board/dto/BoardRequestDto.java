package efub.assignment.community.board.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private Long memberId;
    private String boardName;
    private String description;
    private String notice;
}
