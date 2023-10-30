package com.efub.community.Board.repository;

import com.efub.community.Board.domain.Board;
import com.efub.community.Member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void saveBoard() {
        // given
        Member testMember = Member.builder()
                .email("test@example.com")
                .pw("password")
                .build();
        Board board = Board.builder()
                .boardName("Test Board")
                .boardDesc("Test Description")
                .notice("Test Notice")
                .owner(testMember)
                .build();

        // when
        boardRepository.save(board);

        // then
        Board retrievedBoard = boardRepository.findById(board.getBoardId()).orElse(null);
        assertThat(retrievedBoard).isNotNull();
        assertThat(retrievedBoard.getBoardName()).isEqualTo("Test Board");
    }

    // 제목이 null인 경우
    @Test
    public void saveBoardWithInvalidData() {
        // given
        Board invalidBoard = Board.builder()
                .boardName("")
                .boardDesc("Test Description")
                .notice("Test Notice")
                .build();

        // when & then
        assertThrows(Exception.class, () -> boardRepository.save(invalidBoard));
    }

    // 존재하지 않는 멤버가 게시판을 저장하는 경우
    @Test
    public void saveBoardWithNonExistentMember() {
        // given
        Member nonExistentMember = Member.builder()
                .email("nonexistent@example.com")
                .pw("nonexistentPassword")
                .build();

        Board board = Board.builder()
                .boardName("Test Board")
                .boardDesc("Test Description")
                .notice("Test Notice")
                .owner(nonExistentMember)
                .build();

        // when & then
        assertThrows(Exception.class, () -> boardRepository.save(board));
    }
}