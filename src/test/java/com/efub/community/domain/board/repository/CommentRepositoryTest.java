package com.efub.community.domain.board.repository;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.board.domain.Comment;
import com.efub.community.domain.board.domain.Post;
import com.efub.community.domain.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void save() {
        // given
        final Member member = new Member("test@email.com", "teST1!", "test", 1, "test");
        final Board board = Board.builder().name("test").description("test").owner(member).build();
        final Post post = Post.builder().board(board).content("test").writer(member).anonymous(false).build();
        final Comment comment = Comment.builder().content("test").post(post).anonymous(false).build();

        // when
        final Comment savedComment = commentRepository.save(comment);

        // then
        assertEquals("test", savedComment.getContent());
        assertEquals(post, savedComment.getPost());
    }

    @Test
    void findByPost() {
        // given
        final Member member = new Member("test@email.com", "teST1!", "test", 1, "test");
        final Board board = Board.builder().name("test").description("test").owner(member).build();
        final Post post = Post.builder().board(board).content("test").writer(member).anonymous(false).build();


        // when
        final List<Comment> findByPostResults = commentRepository.findByPost(post);

        // then
        findByPostResults
                .forEach(comment -> {
                    assertThat(comment.getPost()).isEqualTo(post);
                });
    }

}