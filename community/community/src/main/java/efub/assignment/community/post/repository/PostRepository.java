package efub.assignment.community.post.repository;

import efub.assignment.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostIdAndAndWriter_MemberId(Long postId, Long memberId);
}
