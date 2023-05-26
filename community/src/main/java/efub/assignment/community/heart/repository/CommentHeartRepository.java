package efub.assignment.community.heart.repository;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.heart.domain.CommentHeart;
import efub.assignment.community.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentHeartRepository extends JpaRepository<CommentHeart, Long> {
    Boolean existsByMemberAndComment(Member member, Comment comment);

    Optional<CommentHeart> findByMemberAndComment(Member member, Comment comment);
}