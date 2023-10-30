package com.efub.community.Comment.repository;

import com.efub.community.Board.domain.Board;
import com.efub.community.Board.repository.BoardRepository;
import com.efub.community.Comment.domain.Comment;
import com.efub.community.Member.domain.Member;
import com.efub.community.Member.repository.MemberRepository;
import com.efub.community.Post.domain.Post;
import com.efub.community.Post.dto.PostRequestDto;
import com.efub.community.Post.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;


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

        testMember = memberRepository.save(testMember);

        testBoard = Board.builder()
                .boardName("TestBoard")
                .boardDesc("Test Description")
                .owner(testMember)
                .notice("Test Notice")
                .build();

        testBoard = boardRepository.save(testBoard);
    }

    @Test
    public void createComment() {
        // given
        Member writer = Member.builder()
                .email("test@example.com")
                .pw("password")
                .nickname("TestNickname")
                .univ("TestUniversity")
                .studentId("12345")
                .build();

        writer = memberRepository.save(writer);

        Board board = Board.builder()
                .boardName("Test Board")
                .boardDesc("Test Description")
                .notice("Test Notice")
                .owner(writer)
                .build();

        board = boardRepository.save(board);

        // when
        Post post = Post.builder()
                .title("Test Post Title")
                .content("Test Post Content")
                .isAnonymous(false)
                .writer(writer)
                .board(board)
                .build();

        postRepository.save(post);

        Post retrievedPost = postRepository.findById(post.getPostId()).orElse(null);

        // then
        assertThat(retrievedPost).isNotNull();
        assertThat(retrievedPost.getTitle()).isEqualTo("Test Post Title");
    }

    // post에 달린 댓글 전체 조회
    @Test
    public void findCommentsByPost_PostId() {
        // given
        Post post = Post.builder()
                .title("New Post")
                .content("Content of the new post")
                .isAnonymous(false)
                .writer(testMember)
                .board(testBoard)
                .build();

        postRepository.save(post);

        Comment comment1 = new Comment("First comment", testMember, post);
        Comment comment2 = new Comment("Second comment", testMember, post);

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        // when
        List<Comment> comments = commentRepository.findByPost_PostId(post.getPostId());

        // then
        assertThat(comments).isNotNull();
        assertThat(comments).hasSize(2);
    }

    // 작성자가 null인 경우
    @Test
    public void createCommentWithNonExistWriter() {
        // given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .isAnonymous(false)
                .writer(testMember)
                .board(testBoard)
                .build();
        postRepository.save(post);

        // when
        Comment comment = new Comment("댓글", null, post);

        commentRepository.save(comment);

        // then
        boolean exists = commentRepository.existsByPost_PostId(post.getPostId());
        assertFalse(exists);
    }

    // post가 null인 경우
    @Test
    public void createCommentWithoutPost() {
        // given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .isAnonymous(false)
                .writer(testMember)
                .board(testBoard)
                .build();
        postRepository.save(post);

        Comment comment = new Comment("댓글", testMember, null);

        // when & then
        assertThrows(javax.validation.ConstraintViolationException.class, () -> {
            commentRepository.save(comment);
        });
    }
}