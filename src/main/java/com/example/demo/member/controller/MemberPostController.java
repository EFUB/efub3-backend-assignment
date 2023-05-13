package com.example.demo.member.controller;

import com.example.demo.member.service.MemberService;
import com.example.demo.post.domain.Post;
import com.example.demo.post.dto.PostListResponseDto;
import com.example.demo.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts/{memberId}/posts")
@RequiredArgsConstructor
public class MemberPostController {

    // 의존관계 : AccountCommentController -> PostService
    private final PostService postService;
    // 의존관계 : AccountCommentController -> AccountService
    private final MemberService accountService;

    // 특정 유저의 게시글 목록 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PostListResponseDto readAccountPosts(@PathVariable Long memberId) {
        List<Post> postList = postService.findPostListByWriter(memberId);
        return PostListResponseDto.of(postList);
    }
}
