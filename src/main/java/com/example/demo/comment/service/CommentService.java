package com.example.demo.comment.service;


import com.example.demo.comment.domain.Comment;
import com.example.demo.comment.dto.CommentModifyRequestDto;
import com.example.demo.comment.dto.CommentRequestDto;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.member.domain.Member;
import com.example.demo.member.service.MemberService;
import com.example.demo.post.domain.Post;
import com.example.demo.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    // 의존관계 : CommentService -> CommentRepository
    private final CommentRepository commentRepository;  // 반드시 final 적기!! 그래야 RequiredArgsConstructor에 포함됨

    // 의존관계 : CommentService -> PostService
    private final PostService postService;
    // 의존관계 : CommentService -> AccountService
    private final MemberService memberService;

    // 댓글 작성
    public Long createComment(Long postId, CommentRequestDto requestDto) {
        Post post = postService.findPostById(postId);
        Member writer = memberService.findMemberById(requestDto.getMemberId());
        return commentRepository.save(requestDto.toEntity(post, writer)).getCommentId();
    }

    // 댓글 조회 - ID별
    @Transactional(readOnly = true)
    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 댓글입니다. id=" + commentId));
    }

    // 댓글 목록 조회 - 작성자별
    @Transactional(readOnly = true)
    public List<Comment> findCommentListByWriter(Member writer) {
        return commentRepository.findAllByWriter(writer);
    }

    // 댓글 목록 조회 - 게시글별
    @Transactional(readOnly = true)
    public List<Comment> findCommentListByPost(Long postId) {
        Post post = postService.findPostById(postId);
        return commentRepository.findAllByPost(post);
    }

    public Long update(Long commentId, CommentModifyRequestDto requestDto) {
        Comment comment = findCommentById(commentId);
        comment.updateComment(requestDto.getContent());
        return comment.getCommentId();
    }

    @Transactional
    public void deleteComment(Long commentId, Long memberId) {
        Comment comment = commentRepository.findByCommentIdAndAndWriter_MemberId(commentId, memberId);
        commentRepository.delete(comment);
    }
}
