package efub.session.community.comment.domain;

;import efub.session.community.account.domain.Member;
import efub.session.community.global.entity.BaseTimeEntity;
import efub.session.community.heart.domain.CommentHeart;
import efub.session.community.post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id // 이 필드가 이 엔티티의 프라이머리 키이다
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 테이블에 데이터를 넣을 때, 이 필드는 auto_increment로 생성된다
    private Long commentId;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)  // Comment = Many, Post = One
    @JoinColumn(name = "post_id", nullable = false, updatable = false)   // Post 에 있는 post_id과 join될 것
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)  // Comment = Many, Account(댓글 작성자)= One
    @JoinColumn(name = "member_id", nullable = false, updatable = false)
    private Member writer;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL,orphanRemoval = true)
    List<CommentHeart> commentHeartList=new ArrayList<>();

    @Builder    // Builder 클래스를 만들지 않고 @Builder로 간편하게~
    public Comment(String content, Post post, Member writer) {
        this.content = content;
        this.post = post;
        this.writer = writer;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}