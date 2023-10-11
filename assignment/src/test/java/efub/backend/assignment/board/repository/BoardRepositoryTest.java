package efub.backend.assignment.board.repository;

import efub.backend.assignment.board.domain.Board;
import efub.backend.assignment.board.dto.BoardModifyRequestDto;
import efub.backend.assignment.member.domain.Member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE,
	connection = EmbeddedDatabaseConnection.H2)
public class BoardRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private BoardRepository boardRepository;

	@Test
	public void saveBoard() {
		/*
		성공한 테스트코드
		게시판 정보가 저장되었음을 테스트
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

		Board savedBoard = boardRepository.save(board);
		assertThat(savedBoard.getBoardId()).isNotNull();
	}

	@Test
	public void updateBoard() {
		/*
		실패한 테스트코드
		수정되었기에 기존 이름과 달라져서 테스트 통과 못함
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
			.boardTitle("수정 전 제목")
			.content("수정 전 내용")
			.notice("수정 전 알림")
			.boardHost(writer)
			.build();

		// BoardModifyRequestDto 생성자를 사용하여 인스턴스 생성
		BoardModifyRequestDto requestDto = new BoardModifyRequestDto("수정 후 제목", "수정 후 내용", "수정 후 알림", writer.getMemberId());
		board.updateBoard(requestDto, writer);

		assertThat(board.getBoardTitle()).isEqualTo("수정 전 제목");
		assertThat(board.getContent()).isEqualTo("수정 전 내용");
		assertThat(board.getNotice()).isEqualTo("수정 전 알림");
	}

	@Test
	public void findByBoardIdAndBoardHost_MemberId() {
		// findByBoardIdAndBoardHost_MemberId 메서드가 제대로 작동하는지 테스트하는 코드
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


		Optional<Board> foundBoard = boardRepository.findByBoardIdAndBoardHost_MemberId(board.getBoardId(), writer.getMemberId());

		assertThat(foundBoard.isPresent()).isTrue(); // 반환된 게시판이 조회되었음을 확인
		assertThat(foundBoard.get().getBoardTitle()).isEqualTo("Test Title");
	}
}
