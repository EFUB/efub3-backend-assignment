package efub.assignment.community.board.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import efub.assignment.community.board.domain.Board;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.NONE)
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;

	@Test
	public void save(){
		//given
		Board board = Board.builder().title("test board").notice("test notice").description("test").build();

		//when
		Board savedBoard = boardRepository.save(board);

		//then
		assertThat(savedBoard.getTitle()).isEqualTo("test board");
		assertThat(savedBoard.getNotice()).isEqualTo("test notice");
		assertThat(savedBoard.getDescription()).isEqualTo("test");
	}
}
