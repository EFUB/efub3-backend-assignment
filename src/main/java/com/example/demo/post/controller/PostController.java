package com.example.demo.post.controller;

import com.example.demo.post.domain.Post;
import com.example.demo.post.dto.PostCreateRequestDto;
import com.example.demo.post.dto.PostModifyRequestDto;
import com.example.demo.post.dto.PostResponseDto;
import com.example.demo.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성 (POST)
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostResponseDto write(@RequestBody @Valid final PostCreateRequestDto requestDto) {
        return new PostResponseDto(postService.write(requestDto));
    }

    // 게시글 1개 조회 (GET)
    @GetMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto getPost(@PathVariable Long postId) {
        Post findPost = postService.findPostById(postId);
        return new PostResponseDto(findPost);
    }

    // 게시글 내용 수정 (PATCH)
    @PatchMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto update(@PathVariable final Long postId, @RequestBody @Valid final PostModifyRequestDto requestDto) {
        Long id = postService.update(postId, requestDto);
        Post findPost = postService.findPostById(id);
        return new PostResponseDto(findPost);
    }

    // 게시글 삭제 (DELELTE)
    @DeleteMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable Long postId, @RequestParam Long memberId) {
        postService.deletePost(postId, memberId);
        return "성공적으로 삭제가 완료되었습니다.";
    }

}
