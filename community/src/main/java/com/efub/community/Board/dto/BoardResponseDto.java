package com.efub.community.Board.dto;

import com.efub.community.Board.domain.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDto {
    private Long boardId;
    private String boardName;
    private String boardDesc;
    private String notice;
    private Long ownerId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public BoardResponseDto(Board board) {
        this.boardId = board.getBoardId();
        this.boardName = board.getBoardName();
        this.boardDesc = board.getBoardDesc();
        this.notice = board.getNotice();
        this.ownerId = board.getOwner().getMemberId();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }
}