package com.efub.community.Comment.service;

import com.efub.community.Comment.domain.Comment;
import com.efub.community.Comment.dto.CommentRequestDto;
import com.efub.community.Comment.dto.CommentUpdateRequestDto;
import com.efub.community.Comment.repository.CommentRepository;
import com.efub.community.Member.domain.Member;
import com.efub.community.Member.repository.MemberRepository;
import com.efub.community.Post.domain.Post;
import com.efub.community.Post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public Comment createComment(Long postId, CommentRequestDto requestDto){

        Member writer = memberRepository.findById(requestDto.getWriterId())
                .orElseThrow(()->new EntityNotFoundException("계정을 찾을 수 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(()->new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        return commentRepository.save(
                Comment.builder()
                        .content(requestDto.getContent())
                        .writer(writer)
                        .post(post)
                        .build()
        );
    }

    @Transactional(readOnly = true)
    // 개별 댓글 조회
    public Comment findComment(Long commentId){
        return commentRepository.findById(commentId)
                .orElseThrow(()->new EntityNotFoundException("댓글을 찾을 수 없습니다. ID = " + commentId));
    }

    // 게시글에 달린 댓글 전체 조회
    @Transactional(readOnly = true)
    public List<Comment> findCommentList(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new EntityNotFoundException("게시글을 찾을 수 없습니다. ID = " + postId));

        return commentRepository.findAllByPost(post);
    }

    public Comment updateComment(Long commentId, CommentUpdateRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new EntityNotFoundException("댓글을 찾을 수 없습니다. ID = " + commentId));

        if(comment.getWriter().getMemberId().equals(requestDto.getWriterId()))
            comment.updateComment(requestDto);
        else
            throw new IllegalArgumentException("댓글의 작성자만 댓글을 수정할 수 있습니다.");

        return comment;
    }

    public void deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new EntityNotFoundException("존재하지 않는 댓글입니다. ID = " + commentId));

        commentRepository.delete(comment);
    }
}