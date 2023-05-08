package efub.assignment.community.board.service;

import efub.assignment.community.account.domain.Member;
import efub.assignment.community.account.repository.MemberRepository;
import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.BoardModifyRequestDto;
import efub.assignment.community.board.dto.BoardRequestDto;
import efub.assignment.community.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Board addBoard(BoardRequestDto requestDto) {
        Member master = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

        return boardRepository.save(
                Board.builder()
                        .boardName(requestDto.getBoardName())
                        .description(requestDto.getDescription())
                        .notice(requestDto.getNotice())
                        .master(master)
                        .build()
        );
    }

    public List<Board> findBoardList() {
        return boardRepository.findAll();
    }

    public Board findBoard(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다."));
    }

    public Board modifyBoard(Long boardId, BoardModifyRequestDto requestDto) {
        Board board = boardRepository.findByBoardIdAndAndMaster_MemberID(boardId, requestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        board.updateBoard(requestDto);
        return board;
    }

    public void removeBoard(Long boardId, Long memberId) {
        Board board = boardRepository.findByBoardIdAndAndMaster_MemberID(boardId, memberId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        boardRepository.delete(board);
    }
}
