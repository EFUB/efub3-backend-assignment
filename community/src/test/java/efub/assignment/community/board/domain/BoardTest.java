package efub.assignment.community.board.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import efub.assignment.community.board.dto.BoardUpdateRequestDto;
import efub.assignment.community.member.domain.Member;

public class BoardTest {

	@Test
	public void updateBoard() {
		//given
		Member member = Member.builder().email("test@email.com").password("test password").nickname("test nickname")
			.studentId("test studentId").university("test university").build();

		Board board = Board.builder().title("test board").notice("test notice").description("test").build();

		BoardUpdateRequestDto requestDto = BoardUpdateRequestDto.builder().title("new title").notice("new notice")
			.description("new").build();

		//when
		board.updateBoard(requestDto, member);

		//then
		assertThat(board.getTitle()).isEqualTo("new title");
		assertThat(board.getNotice()).isEqualTo("new notice");
		assertThat(board.getDescription()).isEqualTo("new");
		assertThat(board.getOwner()).isEqualTo(member);
	}
}