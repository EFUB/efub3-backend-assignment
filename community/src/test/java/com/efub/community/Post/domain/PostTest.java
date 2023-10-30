package com.efub.community.Post.domain;

import com.efub.community.Board.domain.Board;
import com.efub.community.Member.domain.Member;
import com.efub.community.Post.dto.PostUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PostTest {

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
    public void createPost() {
        Post post = Post.builder()
                .title("Test Title")
                .content("Test Content")
                .isAnonymous(false)
                .writer(testMember)
                .board(testBoard)
                .build();

        // 게시물의 필드 값이 올바른지 확인
        assertThat(post.getTitle()).isEqualTo("Test Title");
        assertThat(post.getContent()).isEqualTo("Test Content");
        assertThat(post.getIsAnonymous()).isEqualTo(false);
        assertThat(post.getWriter()).isEqualTo(testMember);
        assertThat(post.getBoard()).isEqualTo(testBoard);
    }

    // 제목이 null인 경우
    @Test
    public void createPostWithNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            Post.builder()
                    .title(null)
                    .content("Test Content")
                    .isAnonymous(false)
                    .writer(testMember)
                    .board(testBoard)
                    .build();
        });
    }

    // 내용이 null인 경우
    @Test
    public void createPostWithNullContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            Post.builder()
                    .title("Test Title")
                    .content(null)
                    .isAnonymous(false)
                    .writer(testMember)
                    .board(testBoard)
                    .build();
        });
    }


    @Test
    public void updatePost() {
        // 게시물 생성
        Post post = Post.builder()
                .title("Test Title")
                .content("Test Content")
                .isAnonymous(false)
                .writer(testMember)
                .board(testBoard)
                .build();

        // 업데이트할 데이터
        PostUpdateRequestDto updateDto = new PostUpdateRequestDto();
        updateDto.setTitle("Updated Title");
        updateDto.setContent("Updated Content");

        // 게시물 업데이트
        post.updatePost(updateDto);

        // 업데이트된 내용이 제대로 저장되었는지 확인
        assertThat(post.getTitle()).isEqualTo("Updated Title");
        assertThat(post.getContent()).isEqualTo("Updated Content");
    }

    // 유효하지 않은 업데이트 데이터
    @Test
    public void updatePostWithInvalidTitle() {
        // 게시물 생성
        Post post = Post.builder()
                .title("Test Title")
                .content("Test Content")
                .isAnonymous(false)
                .writer(testMember)
                .board(testBoard)
                .build();

        PostUpdateRequestDto updateDto = new PostUpdateRequestDto();
        updateDto.setTitle(null);

        assertThrows(IllegalArgumentException.class, () -> post.updatePost(updateDto));
    }
}