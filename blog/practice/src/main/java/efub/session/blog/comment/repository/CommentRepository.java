package efub.session.blog.comment.repository;

import efub.session.blog.member.domain.Member;
import efub.session.blog.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //작성자별 댓글 목록 조회
    List<Comment> findAllByWriter(Member writer);

    //게시글별 댓글 목록 조회
    List<Comment> findAllByPost(Post post);

    Optional<Post> findByCommentIdAndOwner_MemberId(Long CommentId, Long memberId);
}
