package com.efub.community.domain.board.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.board.domain.Post;
import com.efub.community.domain.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    void save() {
        // given
        final Member member = new Member("test@email.com", "teST1!", "test", 1, "test");
        final Board board = Board.builder().name("test").description("test").owner(member).build();
        final Post post = Post.builder().board(board).content("test").writer(member).anonymous(false).build();

        // when
        final Post savedPost = postRepository.save(post);

        // then
        assertEquals(board, savedPost.getBoard());
        assertEquals(member, savedPost.getWriter());
        assertEquals("test", savedPost.getContent());
    }

    @Test
    void findAllByBoard() {
        // given
        final Member member = new Member("test@email.com", "teST1!", "test", 1, "test");
        final Board board = Board.builder().name("test").description("test").owner(member).build();

        // when
        final List<Post> findAllByBoardResults = postRepository.findAllByBoard(board);

        // then
        findAllByBoardResults
                .forEach(post -> {
                    assertThat(post.getBoard()).isEqualTo(board);
                });
    }

    @Test
    void findAllByOrderByPostIdDesc() {
        // given
        final Member member = new Member("test@email.com", "teST1!", "test", 1, "test");
        final Board board = Board.builder().name("test").description("test").owner(member).build();
        final Post post1 = Post.builder().board(board).content("test").writer(member).anonymous(false).build();
        final Post post2 = Post.builder().board(board).content("test").writer(member).anonymous(false).build();
        List<Post> result = List.of(post2, post1);

        // when
        final List<Post> findAllByOrderByPostIdDescResults = postRepository.findAllByOrderByPostIdDesc();

        // then
        assertEquals(findAllByOrderByPostIdDescResults, result);
    }
}