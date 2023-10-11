package com.efub.community.domain.board.domain;

import com.efub.community.domain.member.domain.Member;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommentTest {
    @Test
    void 댓글_생성_테스트() {
        // given
        final Member member = new Member("test@email.com", "teST1!", "test", 1, "test");
        final Board board = Board.builder().name("test").description("test").owner(member).build();
        final Post post = Post.builder().board(board).content("test").writer(member).anonymous(false).build();

        //when
        final Comment comment = Comment.builder().content("test").post(post).anonymous(false).build();

        //then
        assertThat(comment.getContent()).isEqualTo("test");
        assertThat(comment.getPost()).isEqualTo(post);
        assertThat(comment.isAnonymous()).isEqualTo(false);
    }

    @Test
    void 댓글_수정_테스트() {
        // given
        final Member member = new Member("test@email.com", "teST1!", "test", 1, "test");
        final Board board = Board.builder().name("test").description("test").owner(member).build();
        final Post post = Post.builder().board(board).content("test").writer(member).anonymous(false).build();
        final Comment comment = Comment.builder().content("test").post(post).anonymous(false).build();

        //when
        comment.updateComment("content");

        //then
        assertThat(comment.getContent()).isEqualTo("content");
        assertThat(comment.getPost()).isEqualTo(post);
        assertThat(comment.isAnonymous()).isEqualTo(false);
    }
}