package efub.assignment.community.comment.repository;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByWriter(Member writer);

    List<Comment> findAllByPost(Post post);

    Optional<Comment> findByCommentIdAndWriter_MemberId(Long commentId, Long memberId);
}
