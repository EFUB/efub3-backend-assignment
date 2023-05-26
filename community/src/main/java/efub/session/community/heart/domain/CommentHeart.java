package efub.session.community.heart.domain;
import efub.session.community.account.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import efub.session.community.comment.domain.Comment;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentHeart {
    // 댓글 좋아요 id 작성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_like_Id")
    private Long id;

    // 댓글 - Comment 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "댓글은 필수로 입력되어야 합니다.")
    @JoinColumn(name ="comment_id", updatable = false)
    private Comment comment;

    // 작성자 - Account 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "작성자는 필수로 입력되어야 합니다.")
    @JoinColumn(name = "account_id", updatable = false)
    private Member writer;


    @Builder
    public CommentHeart(Comment comment, Member member) {
        this.comment = comment;
        this.writer = member;

    }
}