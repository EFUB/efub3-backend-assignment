package com.efub.community.Post.controller;

import com.efub.community.Post.domain.Post;
import com.efub.community.Post.dto.PostRequestDto;
import com.efub.community.Post.dto.PostResponseDto;
import com.efub.community.Post.dto.PostUpdateRequestDto;
import com.efub.community.Post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostResponseDto postAdd(@RequestBody PostRequestDto requestDto){
        Post post = postService.createPost(requestDto);

        return new PostResponseDto(post);
    }

    // 전체 조회
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostResponseDto> postListFind(){

        List<Post> postList = postService.findPostList();
        List<PostResponseDto> responseDtoList = new ArrayList<>();

        for(Post post : postList){
            responseDtoList.add(new PostResponseDto(post));
        }
        return responseDtoList;
    }

    // 개별 조회
    @GetMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto findPost(@PathVariable Long postId){
        Post post = postService.findPost(postId);
        return new PostResponseDto(post);
    }

    // 수정
    @PutMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto requestDto){
        Post post = postService.updatePost(postId, requestDto);
        return new PostResponseDto(post);
    }

    // 삭제
    @DeleteMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return "성공적으로 삭제되었습니다.";
    }
}