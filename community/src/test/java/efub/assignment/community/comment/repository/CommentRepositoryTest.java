package efub.assignment.community.comment.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.repository.PostRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommentRepositoryTest {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PostRepository postRepository;

	@Test
	public void save(){
		//given
		Member member = Member.builder().email("test@email.com").password("test password").nickname("test nickname")
			.studentId("test studentId").university("test university").build();
		memberRepository.save(member);
		Post post = Post.builder().title("test post").content("test post content").build();
		postRepository.save(post);
		Comment comment = Comment.builder().content("test comment").writer(member).post(post).build();

		//when
		Comment savedComment = commentRepository.save(comment);

		//then
		assertThat(savedComment.getContent()).isEqualTo("test comment");
	}

	@Test
	public void findAllByPost() {
		//given
		Post post = Post.builder().title("test post").content("test post content").build();
		postRepository.save(post);

		//when
		List<Comment> commentList = commentRepository.findAllByPost(post);

		//then
		commentList.forEach(comment -> assertThat(comment.getPost()).isEqualTo(post));
	}
}