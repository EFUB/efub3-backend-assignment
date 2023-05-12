package efub.session.blog.post.repository;

import efub.session.blog.member.domain.Member;
import efub.session.blog.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findByPostIdAndOwner_MemberId(Long postId, Long memberId);

    List<Post> findAllByWriter(Member writer);
}
