package com.efub.community.Board.domain;

import com.efub.community.Board.dto.BoardUpdateRequestDto;
import com.efub.community.Board.repository.BoardRepository;
import com.efub.community.Member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Autowired
    private BoardRepository boardRepository;

    private Member testMember;

    @BeforeEach
    public void setUp() {
        testMember = Member.builder()
                .email("test@example.com")
                .pw("password")
                .nickname("TestNickname")
                .univ("TestUniversity")
                .studentId("12345")
                .build();
    }

    @Test
    public void updateBoard() {
        // given
        Board board = Board.builder()
                .boardName("Original Board Name")
                .boardDesc("Original Description")
                .notice("Original Notice")
                .owner(testMember)
                .build();

        BoardUpdateRequestDto updateDto = new BoardUpdateRequestDto();
        updateDto.setBoardName("Updated Board Name");
        updateDto.setBoardDesc("Updated Description");
        updateDto.setNotice("Updated Notice");

        // when
        board.updateBoard(updateDto, testMember);

        // then
        assertThat(board.getBoardName()).isEqualTo("Updated Board Name");
        assertThat(board.getBoardDesc()).isEqualTo("Updated Description");
        assertThat(board.getNotice()).isEqualTo("Updated Notice");
    }

    // 제목이 null인 경우
    @Test
    public void updateBoardWithInvalidData() {
        // given
        Board board = Board.builder()
                .boardName("Original Board Name")
                .boardDesc("Original Description")
                .notice("Original Notice")
                .owner(testMember)
                .build();

        BoardUpdateRequestDto updateDto = new BoardUpdateRequestDto();
        updateDto.setBoardName("");
        updateDto.setBoardDesc("Updated Description");
        updateDto.setNotice("Updated Notice");

        // when & then
        assertThrows(Exception.class, () -> board.updateBoard(updateDto, testMember));
    }

    // 존재하지 않는 멤버로 업데이트할 경우
    @Test
    public void updateBoardWithNonExistentMember() {
        // given
        Board board = Board.builder()
                .boardName("Original Board Name")
                .boardDesc("Original Description")
                .notice("Original Notice")
                .owner(testMember)
                .build();
        boardRepository.save(board);

        Member nonExistentMember = Member.builder()
                .email("nonexistent@example.com")
                .pw("nonexistentPassword")
                .build();

        BoardUpdateRequestDto updateDto = new BoardUpdateRequestDto();
        updateDto.setBoardName("Updated Board Name");
        updateDto.setBoardDesc("Updated Description");
        updateDto.setNotice("Updated Notice");

        // when & then
        assertThrows(Exception.class, () -> board.updateBoard(updateDto, nonExistentMember));
    }

}