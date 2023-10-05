package efub.backend.assignment.board.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import efub.backend.assignment.board.dto.BoardModifyRequestDto;
import efub.backend.assignment.member.domain.Member;

class BoardTest {

	private TestEntityManager testEntityManager;

	@Test
	public void successBoardTest() { // 성공한 테스트 코드

		Member writer = Member.builder()
			.email("chaewon1019@ewhain.net")
			.password("chaewon1019!!!")
			.nickname("채원")
			.university("이화여자대학교")
			.studentId("2076216")
			.build();

		Board board = Board.builder()
			.boardTitle("test Title")
			.content("test content")
			.notice("test notice")
			.boardHost(writer)
			.build();

		assertThat(board.getBoardTitle()).isEqualTo("test Title");
		assertThat(board.getContent()).isEqualTo("test content");
		assertThat(board.getNotice()).isEqualTo("test notice");
		assertThat(board.getBoardHost()).isEqualTo(writer);
	}

	@Test
	public void failUpdateBoardTest() { // update와 관련하여 실패한 테스트 코드
		Member writer = Member.builder()
			.email("chaewon1019@ewhain.net")
			.password("chaewon1019!!!")
			.nickname("채원")
			.university("이화여자대학교")
			.studentId("2076216")
			.build();

		Board board = Board.builder()
			.boardTitle("Original title") // 원래 title
			.content("test content")
			.notice("test notice")
			.boardHost(writer)
			.build();

		BoardModifyRequestDto modifyDto = new BoardModifyRequestDto("Update title", "Updated Content", "Updated Notice", writer.getMemberId());
		board.updateBoard(modifyDto, writer);

		assertThat(board.getBoardTitle()).isEqualTo("Original title"); // Update title로 수정되었기에 테스트 실패
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
				// boardTitle은 nullable = false인데 입력하지 않음
				.content("test content")
				.notice("test notice")
				.boardHost(writer)
				.build();

			testEntityManager.persist(board);
			testEntityManager.flush();
		});
	}

}
