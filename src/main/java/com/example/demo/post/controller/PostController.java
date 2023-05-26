package com.example.demo.post.controller;

import com.example.demo.heart.dto.HeartRequestDto;
import com.example.demo.heart.service.PostHeartService;
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
    private final PostHeartService postHeartService;

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

    // 게시글 삭제 (DELETE)
    @DeleteMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable Long postId, @RequestParam Long memberId) {
        postService.deletePost(postId, memberId);
        return "성공적으로 삭제가 완료되었습니다.";
    }

    // 좋아요 추가
    @PostMapping("/{postId}/hearts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createPostHeart(@PathVariable final Long postId, @RequestBody final HeartRequestDto requestDto) {
        postHeartService.create(postId, requestDto.getMemberId());
        return "좋아요를 눌렀습니다.";
    }

    // 좋아요 삭제
    @DeleteMapping("/{postId}/hearts")
    @ResponseStatus(value = HttpStatus.OK)
    public String deletePostHeart(@PathVariable final Long postId, @RequestParam final Long accountId) {
        postHeartService.delete(postId, accountId);
        return "좋아요가 취소되었습니다.";
    }

}
