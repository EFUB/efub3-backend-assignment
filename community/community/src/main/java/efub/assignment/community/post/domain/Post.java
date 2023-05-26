package efub.assignment.community.post.domain;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.heart.domain.PostHeart;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.board.domain.Board;
import efub.assignment.community.global.entity.BaseTimeEntity;
import efub.assignment.community.post.dto.PostModifyRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", updatable = false)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    @Column
    private Boolean isPrivate;  // true면 익명, false면 닉네임 공개

    /*
    mappedBy: 연관 관계의 주인(Owner)
    cascade: 엔티티 삭제 시 연관된 엔티티의 처리 방식
    orphanRemoval: 고아 객체(연관된 부모 엔티티가 없는 자식 엔티티)의 처리 방식
    */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostHeart> postHeartList = new ArrayList<>();

    @Builder
    public Post(Long postId, Board board, String title, String content, Member writer, Boolean isPrivate) {
        this.postId = postId;
        this.board = board;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.isPrivate = isPrivate;

    }

    public void updatePost(PostModifyRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.isPrivate = requestDto.getIsPrivate();
    }
}
