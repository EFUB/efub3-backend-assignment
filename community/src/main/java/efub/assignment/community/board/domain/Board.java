package efub.assignment.community.board.domain;

import efub.assignment.community.board.dto.BoardUpdateRequestDto;
import efub.assignment.community.board.repository.BoardRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.global.entity.BaseTimeEntity;
import efub.assignment.community.member.repository.MemberRepository;
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
    private String title;

    @Column
    private String description;    // desc는 MySQL의 예약어라서 오류 발생

    @Column
    private String notice;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member owner;

    @Builder
    public Board(Long boardId, String title, String description, String notice, Member owner){
        this.boardId = boardId;
        this.title = title;
        this.description = description;
        this.notice = notice;
        this.owner = owner;
    }

    public void updateBoard(BoardUpdateRequestDto requestDto, Member owner) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.notice = requestDto.getNotice();
        this.owner = owner;
    }
}
