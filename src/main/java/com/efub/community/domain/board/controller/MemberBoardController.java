package com.efub.community.domain.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.efub.community.domain.board.domain.Post;
import com.efub.community.domain.board.dto.response.PostListResponseDto;
import com.efub.community.domain.board.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}")
public class MemberBoardController {

	private final PostService postService;

	@GetMapping("/posts")//작성자 별 글 조회
	@ResponseStatus(value = HttpStatus.OK)
	public PostListResponseDto readPostList(@PathVariable final Long accountId) {
		List<Post> postList = postService.findByWriter(accountId);
		return PostListResponseDto.of(postList);
	}
}
