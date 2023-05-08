package efub.assignment.community.board.service;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.BoardCreateRequestDto;
import efub.assignment.community.board.dto.BoardUpdateRequestDto;
import efub.assignment.community.board.repository.BoardRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Board create(BoardCreateRequestDto requestDto) {
        Member owner = memberRepository.findById(requestDto.getOwnerId())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 계정입니다."));

        return boardRepository.save(
                Board.builder()
                        .title(requestDto.getTitle())
                        .description(requestDto.getDescription())
                        .notice(requestDto.getNotice())
                        .owner(owner)
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public Board read(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시판입니다."));
    }

    public Board update(Long boardId, BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시판입니다."));

        Member owner = memberRepository.findById(requestDto.getOwnerId())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 계정입니다."));

        board.updateBoard(requestDto, owner);
        return board;
    }

    public void delete(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시판입니다."));
        boardRepository.delete(board);
    }
}
