package efub.backend.assignment.heart.repository;

import efub.backend.assignment.comment.domain.Comment;
import efub.backend.assignment.heart.domain.CommentHeart;
import efub.backend.assignment.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// 3. 의존성 주입
public interface CommentHeartRepository extends JpaRepository<CommentHeart, Long> {

    Integer countByComment(Comment comment);
    List<CommentHeart> findByWriter(Member member);
    boolean existsByWriterAndComment(Member member, Comment comment);
    Optional<CommentHeart> findByWriterAndComment(Member member, Comment comment);
}