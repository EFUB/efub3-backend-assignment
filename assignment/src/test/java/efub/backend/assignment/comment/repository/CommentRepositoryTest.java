package efub.backend.assignment.comment.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import efub.backend.assignment.comment.domain.Comment;
import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.post.domain.Post;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE,
	connection = EmbeddedDatabaseConnection.H2)
public class CommentRepositoryTest {
	@Autowired
	private TestEntityManager testEntityManager;
	@Autowired
	private CommentRepository commentRepository;
	@Test
	public void saveCommentSuccess() {
		/*
		성공한 테스트코드
		댓글 정보가 저장되었음을 테스트
		 */
		Member writer = Member.builder()
			.email("chaewon1019@ewhain.net")
			.password("chaewon1019!!!")
			.nickname("채원")
			.university("이화여자대학교")
			.studentId("2076216")
			.build();
		testEntityManager.persist(writer);

		Post post = Post.builder()
			.title("Test Post")
			.content("Test Content")
			.writer(writer)
			.build();
		testEntityManager.persist(post);

		Comment comment = Comment.builder()
			.content("Test Comment")
			.post(post)
			.writer(writer)
			.build();

		Comment savedComment = commentRepository.save(comment);
		assertThat(savedComment.getCommentId()).isNotNull();
	}


	@Test
	public void findAllCommentsByPost() {
		/*
		실패한 테스트코드
		게시글별 댓글 목록 조회 -> 댓글 2개이므로 실패한 케이스
		 */

		Member writer = Member.builder()
			.email("chaewon1019@ewhain.net")
			.password("chaewon1019!!!")
			.nickname("채원")
			.university("이화여자대학교")
			.studentId("2076216")
			.build();
		testEntityManager.persist(writer);

		Post post = Post.builder()
			.title("Test Post")
			.content("Test Content")
			.writer(writer)
			.build();
		testEntityManager.persist(post);

		Comment comment1 = Comment.builder()
			.content("Test Comment 1")
			.post(post)
			.writer(writer)
			.build();
		testEntityManager.persist(comment1);

		Comment comment2 = Comment.builder()
			.content("Test Comment 2")
			.post(post)
			.writer(writer)
			.build();
		testEntityManager.persist(comment2);

		List<Comment> commentList = commentRepository.findAllByPost(post);
		assertThat(commentList).hasSize(2);
	}

	@Test
	public void notFindCommentByNonExistingWriterId() {
		/*
		실패한 테스트코드
		commentrepository의 메소드를 활용하여 존재하지 않는 writer로 게시글 검색
		 */
		Member writer = Member.builder()
			.email("chaewon1019@ewhain.net")
			.password("chaewon1019!!!")
			.nickname("채원")
			.university("이화여자대학교")
			.studentId("2076216")
			.build();
		testEntityManager.persist(writer);

		Post post = Post.builder()
			.title("Test Post")
			.content("Test Content")
			.writer(writer)
			.build();
		testEntityManager.persist(post);

		Comment comment = Comment.builder()
			.content("Test Comment")
			.post(post)
			.writer(writer)
			.build();
		testEntityManager.persist(comment);

		Optional<Comment> foundComment = commentRepository.findByCommentIdAndWriter_MemberId(comment.getCommentId(), writer.getMemberId() + 1);  // 존재하지 않는 writer ID

		assertThat(foundComment.isPresent()).isTrue();
	}

}
