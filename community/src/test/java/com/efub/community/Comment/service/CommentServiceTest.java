package com.efub.community.Comment.service;

import com.efub.community.Comment.domain.Comment;
import com.efub.community.Comment.dto.CommentRequestDto;
import com.efub.community.Comment.dto.CommentUpdateRequestDto;
import com.efub.community.Comment.repository.CommentRepository;
import com.efub.community.Member.domain.Member;
import com.efub.community.Member.repository.MemberRepository;
import com.efub.community.Post.domain.Post;
import com.efub.community.Post.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentService commentService;

    private Comment comment;
    private Member writer;
    private Post post;
    private CommentRequestDto requestDto;
    private Long postId = 1L;
    private Long commentId = 1L;
    private Long writerId = 1L;

    @BeforeEach
    void setUp() {
        writer = new Member();
        writer.setMemberId(writerId);
        post = new Post();
        post.setPostId(postId);

        comment = new Comment("Content", writer, post);
        requestDto = new CommentRequestDto();
        requestDto.setContent("Content");
        requestDto.setWriterId(writerId);
    }

    @Test
    void findCommentSuccess() {
        // Given
        given(commentRepository.findById(commentId)).willReturn(Optional.of(comment));

        // When
        Comment foundComment = commentService.findComment(commentId);

        // Then
        assertNotNull(foundComment);
        assertEquals(comment.getContent(), foundComment.getContent());
        verify(commentRepository).findById(commentId);
    }

    @Test
    void findCommentFailure() {
        // Given
        given(commentRepository.findById(commentId)).willReturn(Optional.empty());

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> commentService.findComment(commentId));
    }

    @Test
    void findCommentListSuccess() {
        // Given
        given(postRepository.findById(postId)).willReturn(Optional.of(post));
        given(commentRepository.findAllByPost(post)).willReturn(Collections.singletonList(comment));

        // When
        List<Comment> comments = commentService.findCommentList(postId);

        // Then
        assertNotNull(comments);
        assertFalse(comments.isEmpty());
        assertEquals(1, comments.size());
        verify(commentRepository).findAllByPost(post);
    }

    @Test
    void updateCommentFailure() {
        // Given
        CommentUpdateRequestDto updateRequestDto = new CommentUpdateRequestDto();
        updateRequestDto.setContent("Updated content");
        updateRequestDto.setWriterId(writerId + 1); // Different user

        given(commentRepository.findById(commentId)).willReturn(Optional.of(comment));

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> commentService.updateComment(commentId, updateRequestDto));
    }

    @Test
    void deleteCommentSuccess() {
        // Given
        given(commentRepository.findById(commentId)).willReturn(Optional.of(comment));

        // When
        commentService.deleteComment(commentId);

        // Then
        verify(commentRepository).delete(comment);
    }

    @Test
    void deleteCommentFailure() {
        // Given
        given(commentRepository.findById(commentId)).willReturn(Optional.empty());

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> commentService.deleteComment(commentId));
    }
}