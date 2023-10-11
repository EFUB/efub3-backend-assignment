package efub.backend.assignment.post.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import efub.backend.assignment.board.domain.Board;
import efub.backend.assignment.board.repository.BoardRepository;
import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.member.repository.MemberRepository;
import efub.backend.assignment.post.domain.Post;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE,
	connection = EmbeddedDatabaseConnection.H2)
public class PostRepositoryTest {
	@Autowired
	private TestEntityManager testEntityManager;
	@Autowired
	private BoardRepository boardrepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private MemberRepository memberRepository;

	@Test
	public void savePostSuccess() {
		/*
		성공한 테스트코드
		게시글 정보가 저장되었음
		 */
		Member writer = Member.builder()
			.email("chaewon1019@ewhain.net")
			.password("chaewon1019!!!")
			.nickname("채원")
			.university("이화여자대학교")
			.studentId("2076216")
			.build();
		testEntityManager.persist(writer);

		Board board = Board.builder()
			.boardTitle("Test Title")
			.content("Test Content")
			.notice("Test Notice")
			.boardHost(writer)
			.build();
		testEntityManager.persist(board);

		Post post = Post.builder()
			.title("Test title")
			.content("Test Content")
			.writer(writer)
			.board(board)
			.build();
		testEntityManager.persist(post);

		Optional<Post> foundPost = postRepository.findByPostIdAndWriter_MemberId(post.getPostId(), writer.getMemberId());
		assertThat(memberRepository.existsByEmail(writer.getEmail())).isTrue();
	}

	@Test
	public void writerNullPost() {
		/*
		실패한 테스트코드
		Writer가 Null인 경우
		 */
		Board board = Board.builder()
			.boardTitle("Test Title")
			.content("Test Content")
			.notice("Test Notice")
			.build();

		Post post = Post.builder()
			.title("Test title")
			.content("Test Content")
			.board(board)
			.build();

		assertThrows(Exception.class, () -> {
			testEntityManager.persist(post);
			testEntityManager.flush();  // 예외 발생
		});
	}

	@Test
	public void notFindPostByNonExistingWriterId() {
		/*
		실패한 테스트코드
		postrepository의 메소드를 활용하여 존재하지 않는 writer로 게시글 검색
		 */
		Member writer = Member.builder()
			.email("chaewon1019@ewhain.net")
			.password("chaewon1019!!!")
			.nickname("채원")
			.university("이화여자대학교")
			.studentId("2076216")
			.build();
		testEntityManager.persist(writer);

		Board board = Board.builder()
			.boardTitle("Test Title")
			.content("Test Content")
			.notice("Test Notice")
			.build();
		testEntityManager.persist(board);

		Post post = Post.builder()
			.title("Test title")
			.content("Test Content")
			.writer(writer)
			.board(board)
			.build();
		testEntityManager.persist(post);

		Optional<Post> foundPost = postRepository.findByPostIdAndWriter_MemberId(post.getPostId(), writer.getMemberId() + 1);  // 존재하지 않는 writer ID
		assertThat(foundPost.isPresent()).isFalse();
	}
}
