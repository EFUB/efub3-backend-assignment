package efub.assignment.community.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDto {
    private String boardName;
    private String description;
    private String notice;
    private Long masterId;
}
