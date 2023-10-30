package com.efub.community.domain.board.repository;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.member.domain.Member;
import com.efub.community.domain.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE, connection = EmbeddedDatabaseConnection.H2)
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @DisplayName("게시판을 생성한다")
//    @Order(1)
    void saveBoardTest(){
        Member member = Member.builder()
                .email("gy5027@naver.com")
                .encodedPassword("sdf3dfsS!")
                .nickname("gayoung")
                .studentNo(2094033)
                .university("Ewha Womans University")
                .build();

        Board board1 = Board.builder()
                .description("과외")
                .owner(member)
                .build();

        testEntityManager.persist(board1);

        Board foundBoard = boardRepository.findById(board1.getBoardId()).orElse(null);
        assertThat(foundBoard).isNotNull();
        assertThat(foundBoard.getDescription()).isEqualTo(board1.getDescription());
    }

    @Test
    @DisplayName("게시판을 목록을 조회한다")
    @Transactional
//    @Order(2)
    void selectBoard(){
        Member member = Member.builder()
                .email("gy5027@naver.com")
                .encodedPassword("sdf3dfsS!")
                .nickname("gayoung")
                .studentNo(2094033)
                .university("Ewha Womans University")
                .build();

        Board board1 = Board.builder()
                .description("과외")
                .owner(member)
                .build();
        Board board2 = Board.builder()
                .description("공부")
                .owner(member)
                .build();

        testEntityManager.persist(board1);
        testEntityManager.persist(board2);

        List<Board> findList = boardRepository.findAllByOrderByBoardIdDesc();

        assertThat(findList).isNotEmpty();
        assertThat(findList.size()).isEqualTo(2);

    }


}