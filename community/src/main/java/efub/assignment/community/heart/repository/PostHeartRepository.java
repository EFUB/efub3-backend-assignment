package efub.assignment.community.heart.repository;

import efub.assignment.community.heart.domain.PostHeart;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostHeartRepository extends JpaRepository<PostHeart, Long> {
    Boolean existsByMemberAndPost(Member member, Post post);

    Optional<PostHeart> findByMemberAndPost(Member member, Post post);
}
