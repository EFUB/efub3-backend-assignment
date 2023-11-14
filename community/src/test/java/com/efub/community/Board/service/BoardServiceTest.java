package com.efub.community.Board.service;

import com.efub.community.Board.domain.Board;
import com.efub.community.Board.dto.BoardCreateRequestDto;
import com.efub.community.Board.dto.BoardUpdateRequestDto;
import com.efub.community.Board.repository.BoardRepository;
import com.efub.community.Member.domain.Member;
import com.efub.community.Member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

//import static jdk.internal.vm.compiler.word.LocationIdentity.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private BoardService boardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void createBoardSuccess() {
//        BoardCreateRequestDto requestDto = new BoardCreateRequestDto();
//        requestDto.setOwnerId(1L);
//        requestDto.setBoardName("Sample Board");
//
//        Member member = new Member();
//        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
//
//        Board board = new Board();
//        when(boardRepository.save(any(Board.class))).thenReturn(board);
//
//        assertNotNull(boardService.createBoard(requestDto));
//    }

    @Test
    void createBoardFailureMissingData() {
        BoardCreateRequestDto requestDto = new BoardCreateRequestDto();

        assertThrows(IllegalArgumentException.class, () -> boardService.createBoard(requestDto));
    }

    @Test
    void updateBoardSuccess() {
        BoardUpdateRequestDto requestDto = new BoardUpdateRequestDto();
        requestDto.setOwnerId(1L);

        Board board = new Board();
        Member member = new Member();

        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        assertNotNull(boardService.updateBoard(1L, requestDto));
    }

    @Test
    void updateBoardFailureBoardNotFound() {
        BoardUpdateRequestDto requestDto = new BoardUpdateRequestDto();
        requestDto.setOwnerId(1L);

        when(boardRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> boardService.updateBoard(1L, requestDto));
    }

    @Test
    void deleteBoardSuccess() {
        Board board = new Board();

        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));
        doNothing().when(boardRepository).delete(board);

        assertDoesNotThrow(() -> boardService.deleteBoard(1L));
    }

    @Test
    void deleteBoardFailureBoardNotFound() {
        when(boardRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> boardService.deleteBoard(1L));
    }
}