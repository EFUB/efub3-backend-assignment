package com.efub.community.domain.board.repository;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    void save() {
        // given
        final Member member = new Member("test@email.com", "teST1!", "test", 1, "test");
        final Board board = Board.builder().name("test").description("test").owner(member).build();

        // when
        final Board savedBoard = boardRepository.save(board);

        // then
        assertEquals("test", savedBoard.getName());
        assertEquals("test", savedBoard.getDescription());
        assertEquals(member, savedBoard.getOwner());
    }

    @Test
    void existsByName() {
        // given
        final Member member = new Member("test@email.com", "teST1!", "test", 1, "test");
        final Board board = Board.builder().name("test").description("test").owner(member).build();

        // when
        final boolean existsByNameResult = boardRepository.existsByName("test");

        // then
        assertTrue(existsByNameResult);
    }

    @Test
    void findAllByOrderByBoardIdDesc(){
        // given
        final Member member = new Member("test@email.com", "teST1!", "test", 1, "test");
        final Board board1 = Board.builder().name("test").description("test").owner(member).build();
        final Board board2 = Board.builder().name("test").description("test").owner(member).build();
        List<Board> result = List.of(board2, board1);

        // when
        List<Board> findAllByOrderByBoardIdDescResults = boardRepository.findAllByOrderByBoardIdDesc();

        // then
        assertEquals(findAllByOrderByBoardIdDescResults, result);
    }
}