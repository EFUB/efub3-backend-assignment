package com.efub.community.domain.board.domain;

import com.efub.community.domain.member.domain.Member;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BoardTest {
    @Test
    void 게시판_생성_테스트() {
        // given
        final Member member = new Member("test@email.com", "teST1!", "test", 1, "test");

        //when
        final Board board = Board.builder().name("test").description("test").owner(member).build();

        //then
        assertThat(board.getOwner()).isEqualTo(member);
        assertThat(board.getName()).isEqualTo("test");
        assertThat(board.getDescription()).isEqualTo("test");
    }

    @Test
    void 게시판_수정_테스트() {
        // given
        final Member member = new Member("test@email.com", "teST1!", "test", 1, "test");
        final Member member2 = new Member("test@email.com", "teST1!", "test", 1, "test");

        //when
        final Board board = Board.builder().name("test").description("test").owner(member).build();
        board.updateBoard(member2, "description");

        //then
        assertThat(board.getOwner()).isEqualTo(member2);
        assertThat(board.getDescription()).isEqualTo("description");
    }
}