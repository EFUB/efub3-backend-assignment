package efub.assignment.community.board.repository;

import efub.assignment.community.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByBoardIdAndMaster_MemberId(Long boardId, Long memberId);
}
