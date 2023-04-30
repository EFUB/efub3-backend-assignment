package efub.backend.assignment.post.repository;

import efub.backend.assignment.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostIdAndWriter_MemberId(Long postId, Long memberId);
    //네이밍으로 디비
}