package efub.backend.assignment.board.service;

import efub.backend.assignment.board.domain.Board;
import efub.backend.assignment.board.dto.BoardModifyRequestDto;
import efub.backend.assignment.board.dto.BoardRequestDto;
import efub.backend.assignment.board.repository.BoardRepository;
import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service // 레포지토리만 봄
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Board addBoard(BoardRequestDto requestDto) { // POST
        Member boardHost = memberRepository.findById(requestDto.getBoardHostId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

        return boardRepository.save( // mysql에서는 insert
                Board.builder()
                        .boardTitle(requestDto.getBoardTitle())
                        .content(requestDto.getContent())
                        .notice(requestDto.getNotice())
                        .boardHost(boardHost)
                        .build()
        );
    }

    @Transactional
    public Board findBoard(Long boardId) { // GET
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다."));
    }
    @Transactional
    public void removeBoard(Long boardId) { // DELETE 리턴값 없음
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        // 메소드를 구체화 시켜주는 방법, boardId, memberId에 따라서 접근이 잘못된 것에 대해 알려줄 수 있음
        boardRepository.delete(board); // 아무것도 조회해주지 않기에 굳이 리턴할 필요 없음
    }
    @Transactional
    public Board modifyBoard(Long boardId, BoardModifyRequestDto requestDto) { // PUT
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));

        Member boardHost = memberRepository.findById(requestDto.getBoardHostId())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));

        board.updateBoard(requestDto, boardHost);
        return board;
    }
}