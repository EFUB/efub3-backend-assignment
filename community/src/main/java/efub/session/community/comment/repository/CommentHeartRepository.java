package efub.session.community.comment.repository;

import efub.session.community.comment.domain.Comment;
import efub.session.community.heart.domain.CommentHeart;
import efub.session.community.account.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentHeartRepository extends JpaRepository<CommentHeart,Long> {
    Integer countByComment(Comment comment);
    List<CommentHeart> findByWriter(Member member);
    boolean existsByWriterAndComment(Member member,Comment comment);

    Optional<CommentHeart> findByWriterAndComment(Member member,Comment comment);
}