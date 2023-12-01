package com.efub.community.domain.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efub.community.domain.board.domain.Post;
import com.efub.community.domain.board.dto.request.PostRequestDto;
import com.efub.community.domain.board.dto.response.PostListResponseDto;
import com.efub.community.domain.board.dto.response.PostResponseDto;
import com.efub.community.domain.board.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardPostController {

	private final PostService postService;

	@GetMapping("/{boardId}/posts")
	public PostListResponseDto readPostList(@PathVariable final Long boardId) {
		List<Post> postList = postService.findPostsByBoard(boardId);
		return PostListResponseDto.of(postList);
	}

	@PostMapping("/{boardId}/posts")
	public PostResponseDto createPost(@PathVariable final Long boardId,
		@RequestBody @Valid final PostRequestDto requestDto) {
		Long id = postService.create(boardId, requestDto);
		Post post = postService.findById(id);
		return PostResponseDto.of(post);
	}
}
