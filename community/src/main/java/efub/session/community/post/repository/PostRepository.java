package efub.session.community.post.repository;

import efub.session.community.account.domain.Member;
import efub.session.community.account.dto.PostModifyRequestDto;
import efub.session.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostIdAndWriter_MemberId(Long postId, Long memberId); // POST로 접근하는 매개체, pk 타입이 long

    List<Post> findAllByWriter(Member member);
}
