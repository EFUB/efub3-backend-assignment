package efub.backend.assignment.comment.repository;

import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.comment.domain.Comment;
import efub.backend.assignment.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 작성자(writer)별 댓글 목록 조회
    List<Comment> findAllByWriter(Member writer);

    // 게시글(post)별 댓글 목록 조회
    List<Comment> findAllByPost(Post post);

    // 댓글 조회
    Optional<Comment> findByCommentIdAndWriter_MemberId(Long commentId,Long memberId);

}
