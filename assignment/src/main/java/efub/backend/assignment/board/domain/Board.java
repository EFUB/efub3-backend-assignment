package efub.backend.assignment.board.domain;

import efub.backend.assignment.board.dto.BoardModifyRequestDto;
import efub.backend.assignment.global.entity.BaseTimeEntity;
import efub.backend.assignment.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // table과 mapping
@NoArgsConstructor // 기본 생성자를 생성
@Getter
public class Board extends BaseTimeEntity {

    @Id // PK와 mapping
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId; // 카멜식 표기, mysql에서는 board_id

    @Column
    private String boardTitle;

    @Column(columnDefinition = "TEXT") // string이 아니라 text임을 명시
    private String content;

    @Column(columnDefinition = "TEXT") // string이 아니라 text임을 명시
    private String notice;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member boardHost; // board 작성자의 변수명 boardHost

    @Builder
    public Board(Long boardId, String boardTitle, String content, String notice, Member boardHost) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.content = content;
        this.notice = notice;
        this.boardHost = boardHost;
    }

    public void updateBoard(BoardModifyRequestDto requestDto, Member boardHost) {
        this.boardTitle = requestDto.getBoardTitle();
        this.content = requestDto.getContent();
        this.notice = requestDto.getNotice();
        this.boardHost = boardHost;
    }
}