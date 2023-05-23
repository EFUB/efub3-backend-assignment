package com.example.demo.heart.service;

import com.example.demo.comment.domain.Comment;
import com.example.demo.comment.service.CommentService;
import com.example.demo.heart.domain.CommentHeart;
import com.example.demo.heart.repository.CommentHeartRepository;
import com.example.demo.member.domain.Member;
import com.example.demo.member.dto.MemberInfoRequestDto;
import com.example.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentHeartService {
    private final CommentService commentService;
    private final CommentHeartRepository commentHeartRepository;

    private final MemberService memberService;

    public void create(Long commentId, MemberInfoRequestDto requestDto) {
        Member member = memberService.findMemberById(requestDto.getMemberId());
        Comment comment = commentService.findCommentById(commentId);
        if (isExistsByWriterAndComment(member, comment)) throw new RuntimeException("이미 좋아요를 눌렀습니다.");
        CommentHeart commentHeart = CommentHeart.builder()
                .comment(comment)
                .account(member)
                .build();
        commentHeartRepository.save(commentHeart);
    }

    public void delete(Long commentId, Long memberId) {
        Member member = memberService.findMemberById(memberId);
        Comment comment = commentService.findCommentById(commentId);
        CommentHeart commentHeart = commentHeartRepository.findByWriterAndComment(member, comment)
                .orElseThrow(() -> new IllegalArgumentException("해당 좋아요가 없습니다."));
        commentHeartRepository.delete(commentHeart);
    }

    @Transactional(readOnly = true)
    private boolean isExistsByWriterAndComment(Member member, Comment comment) {
        return commentHeartRepository.existsByWriterAndComment(member, comment);
    }

    // 한 댓글의 좋아요 개수를 리턴
    @Transactional(readOnly = true)
    public Integer countCommentHeart(Comment comment) {
        return commentHeartRepository.countByComment(comment);
    }
}
