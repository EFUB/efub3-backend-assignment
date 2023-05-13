package com.example.demo.member.controller;

import com.example.demo.comment.domain.Comment;
import com.example.demo.comment.service.CommentService;
import com.example.demo.member.domain.Member;
import com.example.demo.member.dto.MemberCommentsResponseDto;
import com.example.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts/{accountId}/comments")
@RequiredArgsConstructor
public class MemberCommentController {

    // 의존관계 : AccountCommentController -> CommentService
    public final CommentService commentService;
    // 의존관계 : AccountCommentController -> AccountService
    private final MemberService memberService;

    // 특정 유저의 댓글 목록 조회
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public MemberCommentsResponseDto readAccountComments(@PathVariable Long accountId) {
        Member member = memberService.findMemberById(accountId);
        List<Comment> commentList = commentService.findCommentListByWriter(member);
        return MemberCommentsResponseDto.of(member.getNickname(), commentList);
    }

}
