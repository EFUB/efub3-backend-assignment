package efub.session.blog.post.domain;

import efub.session.blog.board.domain.Board;
import efub.session.blog.global.BaseTimeEntity;
import efub.session.blog.member.domain.Member;
import efub.session.blog.post.dto.PostModifyRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    private Boolean anonymous;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member owner;

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Post(Boolean anonymous, String content, Member owner, Board board){
        this.anonymous=anonymous;
        this.content=content;
        this.owner=owner;
        this.board=board;
    }

    public void updatePost(PostModifyRequestDto requestDto){
        this.anonymous=requestDto.getAnonymous();
        this.content=requestDto.getContent();
    }

}
