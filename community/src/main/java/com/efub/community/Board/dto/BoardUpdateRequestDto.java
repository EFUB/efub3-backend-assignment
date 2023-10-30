package com.efub.community.Board.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateRequestDto {
    private String boardName;
    private String boardDesc;
    private String notice;
    private Long ownerId;

    @Builder
    public BoardUpdateRequestDto(String boardName, String boardDesc, String notice, Long ownerId){
        this.boardName = boardName;
        this.boardDesc = boardDesc;
        this.notice = notice;
        this.ownerId = ownerId;
    }
}
