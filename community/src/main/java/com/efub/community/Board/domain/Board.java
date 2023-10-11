package com.efub.community.Board.domain;

import com.efub.community.Board.dto.BoardUpdateRequestDto;
import com.efub.community.Member.domain.Member;
import com.efub.community.Member.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column
    private String boardName;

    @Column
    private String boardDesc;

    @Column
    private String notice;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member owner;

    @Builder
    public Board(Long boardId, String boardName, String boardDesc, String notice, Member owner){
        this.boardId = boardId;
        this.boardName = boardName;
        this.boardDesc = boardDesc;
        this.notice = notice;
        this.owner = owner;
    }

    public void updateBoard(BoardUpdateRequestDto requestDto, Member owner) {
        this.boardName = requestDto.getBoardName();
        this.boardDesc = requestDto.getBoardDesc();
        this.notice = requestDto.getNotice();
        this.owner = owner;
    }
}
