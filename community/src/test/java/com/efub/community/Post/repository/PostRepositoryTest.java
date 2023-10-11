package com.efub.community.Post.repository;

import com.efub.community.Board.domain.Board;
import com.efub.community.Member.domain.Member;
import com.efub.community.Post.domain.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    private Member testMember;
    private Board testBoard;

    @BeforeEach
    public void setUp() {
        testMember = Member.builder()
                .email("test@example.com")
                .pw("password")
                .nickname("TestNickname")
                .univ("TestUniversity")
                .studentId("12345")
                .build();

        testBoard = Board.builder()
                .boardName("Test Board")
                .boardDesc("Test Description")
                .notice("Test Notice")
                .owner(testMember)
                .build();
    }

    @Test
    public void testSavePost() {
        Post post = Post.builder()
                .title("Test Title")
                .content("Test Content")
                .isAnonymous(false)
                .writer(testMember)
                .board(testBoard)
                .build();
        postRepository.save(post);

        Post retrievedPost = postRepository.findById(post.getPostId()).orElse(null);

        assertThat(retrievedPost).isNotNull();
        assertThat(retrievedPost.getTitle()).isEqualTo("Test Title");
    }

    @Test
    public void testFindAllPostsByBoard() {
        Post post1 = Post.builder()
                .title("Post 1")
                .content("Content 1")
                .isAnonymous(false)
                .writer(testMember)
                .board(testBoard)
                .build();
        postRepository.save(post1);

        Post post2 = Post.builder()
                .title("Post 2")
                .content("Content 2")
                .isAnonymous(false)
                .writer(testMember)
                .board(testBoard)
                .build();
        postRepository.save(post2);

        Iterable<Post> postsInBoard = postRepository.findByBoard(testBoard);

        int count = 0;
        for (Post post : postsInBoard) {
            count++;
        }
        assertThat(count).isEqualTo(2);
    }

    // 존재하지 않는 게시판으로 게시물을 조회하는 경우
    @Test
    public void testFindPostsByNonExistentBoard() {
        // 존재하지 않는 게시판으로 게시물을 조회할 때, 빈 목록이 반환되어야 함
        Board nonExistentBoard = new Board();
        List<Post> postsInNonExistentBoard = postRepository.findByBoard(nonExistentBoard);
        assertEquals(0, postsInNonExistentBoard.size());
    }

    // 유효하지 않은 데이터로 게시물을 저장하는 경우
    @Test
    public void testSavePostWithInvalidData() {
        Post invalidPost = Post.builder()
                .title("")
                .content("Test Content")
                .isAnonymous(false)
                .writer(testMember)
                .board(testBoard)
                .build();

        assertThrows(Exception.class, () -> postRepository.save(invalidPost));
    }
}