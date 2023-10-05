package efub.backend.assignment.post.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import efub.backend.assignment.board.domain.Board;
import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.post.dto.PostModifyRequestDto;

class PostTest {
	private TestEntityManager testEntityManager;

	@Test
	public void createPostSuccess() { // 성공한 테스트 코드
		Member writer = Member.builder()
			.email("chaewon1019@ewhain.net")
			.password("chaewon1019!!!")
			.nickname("채원")
			.university("이화여자대학교")
			.studentId("2076216")
			.build();

		Board board = Board.builder()
			.boardTitle("Test Board Title")
			.content("Test Content")
			.notice("Test Notice")
			.boardHost(writer)
			.build();

		Post post = Post.builder()
			.title("Test title")
			.content("Test Content")
			.writer(writer)
			.board(board)
			.build();

		assertThat(post.getTitle()).isEqualTo("Test title");
		assertThat(post.getContent()).isEqualTo("Test Content");
		assertThat(post.getWriter()).isEqualTo(writer);
		assertThat(post.getBoard()).isEqualTo(board);
	}

	@Test
	public void failUpdatePostTest() { // update와 관련하여 실패한 테스트 코드
		Member writer = Member.builder()
			.email("chaewon1019@ewhain.net")
			.password("chaewon1019!!!")
			.nickname("채원")
			.university("이화여자대학교")
			.studentId("2076216")
			.build();

		Board board = Board.builder()
			.boardTitle("Test Board Title")
			.content("test content")
			.notice("test notice")
			.boardHost(writer)
			.build();

		Post post = Post.builder()
			.title("Original Post Title")
			.content("Test Content")
			.writer(writer)
			.board(board)
			.build();

		PostModifyRequestDto modifyDto = new PostModifyRequestDto(writer.getMemberId(),"Updated Post Title", "Updated Content");
		post.updatePost(modifyDto);


		assertThat(post.getTitle()).isEqualTo("Original Post Title"); // Update Post Title로 수정되었기에 테스트 실패
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
				.title(null) // title은 nullable=false인데 null로 입력시, 실패
				.content("Test Content")
				.writer(writer)
				.board(board)
				.build();

			testEntityManager.persist(post);
			testEntityManager.flush();
		});
	}

}
