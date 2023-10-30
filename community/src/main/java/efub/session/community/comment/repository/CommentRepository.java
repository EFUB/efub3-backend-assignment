package efub.session.community.comment.repository;

import efub.session.community.account.domain.Member;
import efub.session.community.comment.domain.Comment;
import efub.session.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 작성자(writer)별 댓글 목록 조회
    List<Comment> findAllByWriter(Member writer);

    // 게시글(post)별 댓글 목록 조회
    List<Comment> findAllByPost(Post post);

    Comment findByCommentIdAndAndWriter_MemberId(Long commentId, Long memberId);

    Comment findCommentByCommentId(Long commentId);
}
