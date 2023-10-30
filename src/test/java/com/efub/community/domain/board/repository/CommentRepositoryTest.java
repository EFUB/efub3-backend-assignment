package com.efub.community.domain.board.repository;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.board.domain.Comment;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE, connection = EmbeddedDatabaseConnection.H2)
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void findByWriterTest_success(){ //다른 레포지토리도 주입하기 애매해서, 엔티티에서 cascade = CascadeType.ALL를 추가해서 테스트했습니다.
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

        Comment comment = Comment.builder()
                .writer(member)
                .post(post)
                .anonymous(true)
                .content("댓글1")
                .build();

        commentRepository.save(comment);

        List<Comment> commentList = commentRepository.findByWriter(member);

        assertThat(commentList).isNotEmpty();
        assertThat(commentList.size()).isEqualTo(1);
    }


    @Test
    void findByWriterTest_failed(){
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

        Comment comment = Comment.builder()
                .writer(member)
                .post(post)
                .anonymous(true)
                .content("댓글1")
                .build();

        commentRepository.save(comment);

        List<Comment> commentList = commentRepository.findByWriter(member);

        assertThat(commentList).isNotEmpty();
        assertThat(commentList.size()).isEqualTo(2);
    }

}