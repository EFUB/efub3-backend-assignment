package efub.assignment.community.board.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {
    private String boardName;
    private String description;
    private String notice;
    private Long masterId;
}
