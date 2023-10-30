package com.efub.community.Board.service;

import com.efub.community.Board.domain.Board;
import com.efub.community.Board.dto.BoardCreateRequestDto;
import com.efub.community.Board.dto.BoardUpdateRequestDto;
import com.efub.community.Board.repository.BoardRepository;
import com.efub.community.Member.domain.Member;
import com.efub.community.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 게시판 생성
    public Board createBoard(BoardCreateRequestDto requestDto) {
        // 입력 데이터의 유효성을 검증
        if (requestDto.getOwnerId() == null || requestDto.getBoardName() == null) {
            throw new IllegalArgumentException("올바른 입력 데이터가 필요합니다.");
        }

        Member owner = memberRepository.findById(requestDto.getOwnerId())
                .orElseThrow(()-> new IllegalArgumentException("계정이 존재하지 않습니다."));

        return boardRepository.save(
                Board.builder()
                        .boardName(requestDto.getBoardName())
                        .boardDesc(requestDto.getBoardDesc())
                        .notice(requestDto.getNotice())
                        .owner(owner)
                        .build()
        );
    }

    // 게시판 조회
    @Transactional(readOnly = true)
    public Board readBoard(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("게시판을 찾을 수 없습니다. ID : " + boardId));
    }

    // 게시판 수정
    public Board updateBoard(Long boardId, BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("게시판을 찾을 수 없습니다. ID : " + boardId));

        Member owner = memberRepository.findById(requestDto.getOwnerId())
                .orElseThrow(()-> new IllegalArgumentException("계정을 찾을 수 없습니다. ID : " + requestDto.getOwnerId()));

        board.updateBoard(requestDto, owner);

        return board;
    }

    // 게시판 삭제
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("게시판을 찾을 수 없습니다. ID : " + boardId));
        boardRepository.delete(board);
    }
}
