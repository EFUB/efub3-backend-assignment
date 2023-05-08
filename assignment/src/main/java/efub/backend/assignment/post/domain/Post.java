package efub.backend.assignment.post.domain;

import efub.backend.assignment.board.domain.Board;
import efub.backend.assignment.global.entity.BaseTimeEntity;
import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.post.dto.PostModifyRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId; // 카멜식 표기, mysql에서는 post_id

    @Column
    private String title;

    @Column(columnDefinition = "TEXT") // string이 아니라 text임을 명시
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer; // post의 글 작성자이므로 변수명을 writer로

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board; // post의 글 작성자이므로 변수명을 writer로

    @Builder
    public Post(Long postId, String title, String content, Member writer, Board board) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.board = board;
    }

    public void updatePost(PostModifyRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

}