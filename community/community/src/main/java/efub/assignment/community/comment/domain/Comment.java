package efub.assignment.community.comment.domain;

import efub.assignment.community.global.entity.BaseTimeEntity;
import efub.assignment.community.heart.domain.CommentHeart;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)  // 지연 로딩 사용을 명시적으로 설정하기
    @JoinColumn(name = "post_id", nullable = false, updatable = false)  // FK로 사용되는 컬럼 지정
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, updatable = false)
    private Member writer;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CommentHeart> commentHeartList = new ArrayList<>();

    @Builder
    public Comment(String content, Post post, Member writer) {
        this.content = content;
        this.post = post;
        this.writer = writer;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}
