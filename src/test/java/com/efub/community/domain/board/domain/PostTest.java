package com.efub.community.domain.board.domain;

import com.efub.community.domain.member.domain.Member;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PostTest {
    @Test
    void 게시글_생성_테스트() {
        // given
        final Member member = new Member("test@email.com", "teST1!", "test", 1, "test");
        final Board board = Board.builder().name("test").description("test").owner(member).build();

        //when
        final Post post = Post.builder().board(board).content("test").writer(member).anonymous(false).build();

        //then
        assertThat(post.getBoard()).isEqualTo(board);
        assertThat(post.getContent()).isEqualTo("test");
        assertThat(post.getWriter()).isEqualTo(member);
    }

    @Test
    void 게시글_수정_테스트() {
        // given
        final Member member = new Member("test@email.com", "teST1!", "test", 1, "test");
        final Board board = Board.builder().name("test").description("test").owner(member).build();
        final Post post = Post.builder().board(board).content("test").writer(member).anonymous(false).build();

        //when
        post.updatePost("content");

        //then
        assertThat(post.getBoard()).isEqualTo(board);
        assertThat(post.getContent()).isEqualTo("content");
        assertThat(post.getWriter()).isEqualTo(member);
    }
}