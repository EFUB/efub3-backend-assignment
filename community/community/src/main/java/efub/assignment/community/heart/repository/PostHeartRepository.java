package efub.assignment.community.heart.repository;

import efub.assignment.community.heart.domain.PostHeart;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostHeartRepository extends JpaRepository<PostHeart, Long> {
    Integer countByPost(Post post);

    List<PostHeart> findByWriter(Member member);

    boolean existsByWriterAndPost(Member member, Post post);

    Optional<PostHeart> findByWriterAndPost(Member member, Post post);
}
