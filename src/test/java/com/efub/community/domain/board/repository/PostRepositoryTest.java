package com.efub.community.domain.board.repository;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.board.domain.Post;
import com.efub.community.domain.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE, connection = EmbeddedDatabaseConnection.H2)
class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Test
    public void findAllTest(){

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

        Post post = Post.builder()
                .writer(member)
                .board(board1)
                .anonymous(true)
                .content("안녕하세요")
                .build();

        Post post2 = Post.builder()
                .writer(member)
                .board(board1)
                .anonymous(true)
                .content("안녕하세요")
                .build();

        postRepository.save(post);
        postRepository.save(post2);

        List<Post> postList = postRepository.findAllByOrderByPostIdDesc();

        assertThat(postList.size()).isEqualTo(1);
    }

    @Test
    void findByWriterTest(){
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

        Post post = Post.builder()
                .writer(member)
                .board(board1)
                .anonymous(true)
                .content("안녕하세요")
                .build();

        postRepository.save(post);

        List<Post> foundPostList = postRepository.findByWriter(member);

        assertThat(foundPostList).isNotEmpty();
        assertThat(foundPostList.size()).isEqualTo(1);
    }

}