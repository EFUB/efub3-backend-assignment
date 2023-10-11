package efub.assignment.community.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
{
    "title" : "벗들의 벼룩",
    "desc" : "벗들의 중고거래 모임",
    "notice" : "게시판지기가 변경되었습니다.",
    "ownerId" : 2
}
 */
@Getter
@NoArgsConstructor
public class BoardUpdateRequestDto {
    private String title;
    private String description;
    private String notice;
    private Long ownerId;

    @Builder
    public BoardUpdateRequestDto(String title, String description, String notice, Long ownerId){
        this.title = title;
        this.description = description;
        this.notice = notice;
		this.ownerId = ownerId;
    }
}
