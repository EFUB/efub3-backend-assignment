package efub.session.blog.heart.domain;

import efub.session.blog.comment.domain.Comment;
import efub.session.blog.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

// 1. entity 매핑
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentHeart {
    // 댓글 좋아요 id 작성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_like_id")
    private Long id;

    // 댓글 - Comment 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "댓글은 필수로 입력되어야 합니다.")
    @JoinColumn(name = "comment_id",updatable = false)
    private Comment comment;

    // 작성자 - Account 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "작성자는 필수로 입력되어야 합니다.")
    @JoinColumn(name = "member_id",updatable = false)
    private Member writer;

    @Builder
    public CommentHeart(Comment comment, Member member) {
        this.comment = comment;
        this.writer=member;
    }
}
