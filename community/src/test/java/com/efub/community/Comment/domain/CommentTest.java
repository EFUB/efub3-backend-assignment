package com.efub.community.Comment.domain;

import com.efub.community.Comment.repository.CommentRepository;
import com.efub.community.Member.domain.Member;
import com.efub.community.Member.repository.MemberRepository;
import com.efub.community.Post.domain.Post;
import com.efub.community.Post.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CommentTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CommentRepository commentRepository;

    private Member testMember;
    private Post testPost;

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

        testPost = Post.builder()
                .title("Test Post Title")
                .content("Test Post Content")
                .isAnonymous(false)
                .writer(testMember)
                .build();

        postRepository.save(testPost);
    }

    @Test
    public void createComment() {
        // given
        Comment comment = Comment.builder()
                .content("Test Comment Content")
                .writer(testMember)
                .post(testPost)
                .build();

        // when
        commentRepository.save(comment);

        // then
        Comment retrievedComment = commentRepository.findById(comment.getCommentId()).orElse(null);
        assertThat(retrievedComment).isNotNull();
        assertThat(retrievedComment.getContent()).isEqualTo("Test Comment Content");
    }

    // 내용이 null인 경우
    @Test
    public void createCommentWithInvalidData() {
        // given
        Comment invalidComment = Comment.builder()
                .content("")
                .writer(testMember)
                .post(testPost)
                .build();

        // when & then
        assertThrows(Exception.class, () -> commentRepository.save(invalidComment));
    }

    // 존재하지 않는 멤버가 댓글을 작성하는 경우
    @Test
    public void createCommentWithNonExistentMember() {
        // given
        Member nonExistentMember = Member.builder()
                .email("nonexistent@example.com")
                .pw("password")
                .nickname("NonExistentNickname")
                .univ("NonExistentUniversity")
                .studentId("9999")
                .build();

        memberRepository.save(nonExistentMember);

        // when & then
        Comment comment = Comment.builder()
                .content("Test Comment Content")
                .writer(nonExistentMember)
                .post(testPost)
                .build();

        assertThrows(Exception.class, () -> commentRepository.save(comment));
    }
}