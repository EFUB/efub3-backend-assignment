package com.efub.community.Board.controller;

import com.efub.community.Board.domain.Board;
import com.efub.community.Board.dto.BoardCreateRequestDto;
import com.efub.community.Board.dto.BoardResponseDto;
import com.efub.community.Board.dto.BoardUpdateRequestDto;
import com.efub.community.Board.service.BoardService;
import com.efub.community.Post.domain.Post;
import com.efub.community.Post.dto.PostResponseDto;
import com.efub.community.Post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final PostService postService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BoardResponseDto createBoard(@RequestBody BoardCreateRequestDto requestDto){
        Board board = boardService.createBoard(requestDto);
        return new BoardResponseDto(board);
    }

    @GetMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponseDto findBoard(@PathVariable Long boardId){
        Board board = boardService.readBoard(boardId);
        return new BoardResponseDto(board);
    }

    // 게시판에 속한 글들을 모두 조회
    @GetMapping("/{boardId}/posts")
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostResponseDto> postListInBoard(@PathVariable Long boardId){

        List<Post> postList = postService.findPostsInBoard(boardId);
        List<PostResponseDto> responseDtoList = new ArrayList<>();

        for(Post post : postList){
            responseDtoList.add(new PostResponseDto(post));
        }
        return responseDtoList;

    }

    @PutMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponseDto updateBoard(@PathVariable Long boardId, @RequestBody BoardUpdateRequestDto requestDto){
        Board board = boardService.updateBoard(boardId, requestDto);
        return new BoardResponseDto(board);
    }

    @DeleteMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteBoard(@PathVariable Long boardId){

        List<Post> postList = postService.findPostsInBoard(boardId);
        for (Post p: postList) {
            postService.deletePost(p.getPostId());
        }

        boardService.deleteBoard(boardId);
        return "성공적으로 삭제되었습니다.";
    }
}
