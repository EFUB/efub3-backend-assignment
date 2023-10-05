package efub.backend.assignment.comment.domain;

import efub.backend.assignment.board.domain.Board;
import efub.backend.assignment.comment.dto.CommentModifyRequestDto;
import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.post.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CommentTest {

	private TestEntityManager testEntityManager;

	@Test
	public void createCommentSuccess() { // 성공한 테스트 코드
		Member writer = Member.builder()
			.email("chaewon1019@ewhain.net")
			.password("chaewon1019!!!")
			.nickname("채원")
			.university("이화여자대학교")
			.studentId("2076216")
			.build();

		Post post = Post.builder()
			.title("Test Post")
			.content("Test Content")
			.writer(writer)
			.build();

		Comment comment = Comment.builder()
			.content("Test Comment")
			.post(post)
			.writer(writer)
			.build();

		assertThat(comment.getContent()).isEqualTo("Test Comment");
		assertThat(comment.getWriter()).isEqualTo(writer);
		assertThat(comment.getPost()).isEqualTo(post);
	}

	@Test
	public void failUpdateCommentTest() { // update와 관련하여 실패한 테스트 코드
		Member writer = Member.builder()
			.email("chaewon1019@ewhain.net")
			.password("chaewon1019!!!")
			.nickname("채원")
			.university("이화여자대학교")
			.studentId("2076216")
			.build();

		Board board = Board.builder()
			.boardTitle("Original title")
			.content("test content")
			.notice("test notice")
			.boardHost(writer)
			.build();

		Post post = Post.builder()
			.title("Test Post")
			.content("Test Content")
			.writer(writer)
			.build();

		Comment comment = Comment.builder()
			.content("Original Comment")
			.post(post)
			.writer(writer)
			.build();

		CommentModifyRequestDto modifyDto = new CommentModifyRequestDto(writer.getMemberId(), "Updated Comment");
		comment.updateComment(modifyDto);

		assertThat(comment.getContent()).isEqualTo("Original Comment"); // Update Comment로 수정되었기에 테스트 실패
	}

	@Test
	public void failBoardTest_NULL() { // 실패한 테스트 코드

		assertThrows(Exception.class, () -> {
			Member writer = Member.builder()
				.email("chaewon1019@ewhain.net")
				.password("chaewon1019!!!")
				.nickname("채원")
				.university("이화여자대학교")
				.studentId("2076216")
				.build();

			Board board = Board.builder()
				.boardTitle("test title")
				.content("test content")
				.notice("test notice")
				.boardHost(writer)
				.build();

			Post post = Post.builder()
				.title("Test Post")
				.content("Test Content")
				.writer(writer)
				.build();

			Comment comment = Comment.builder()
				.content(null) // content는 nullable=false인데 null로 입력시, 실패
				.post(post)
				.writer(writer)
				.build();

			testEntityManager.persist(comment);
			testEntityManager.flush();
		});
	}
}
