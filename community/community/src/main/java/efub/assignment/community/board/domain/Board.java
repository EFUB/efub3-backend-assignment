package efub.assignment.community.board.domain;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.board.dto.BoardModifyRequestDto;
import efub.assignment.community.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column
    private String boardName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String notice;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member master;

    @Builder
    public Board(Long boardId, String boardName, String description, String notice, Member master) {
        this.boardId = boardId;
        this.boardName = boardName;
        this.description = description;
        this.notice = notice;
        this.master = master;
    }

    public void updateBoard(BoardModifyRequestDto requestDto) {
        this.boardName = requestDto.getBoardName();
        this.description = requestDto.getDescription();
        this.notice = requestDto.getNotice();
        this.master = master;
    }
}
