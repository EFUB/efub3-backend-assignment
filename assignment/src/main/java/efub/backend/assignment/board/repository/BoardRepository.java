package efub.backend.assignment.board.repository;

import efub.backend.assignment.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByBoardIdAndBoardHost_MemberId(Long boardId, Long memberId);
    //네이밍으로 디비
}
