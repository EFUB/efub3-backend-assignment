package com.example.demo.board.dto;

import com.example.demo.board.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
{
		"title": "벗들의 산책",
		"desc": "같이 산책할 벗을 구하는 게시판입니다",
    "notice": "위치, 시간, 인원을 꼭 명시해주세요",
    "ownerId" : "101",
		"created": "2023-01-23",
		"updated": "2023-02-23",
		"id": "204"
}
 */

@Getter
@NoArgsConstructor
public class BoardResponseDto {

    private Long boardId;

    private String title;

    private String description;

    private String pinned;

    private Long ownerId;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public BoardResponseDto (Board board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.pinned = board.getPinned();
        this.ownerId = board.getOwner().getMemberId();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
    }

}
