package com.efub.community.domain.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.board.dto.request.BoardRequestDto;
import com.efub.community.domain.board.dto.response.BoardListResponseDto;
import com.efub.community.domain.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

	private final BoardService boardService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public BoardListResponseDto.SingleBoard createBoard(@RequestBody @Valid final BoardRequestDto requestDto) {
		Long id = boardService.create(requestDto);
		Board board = boardService.findById(id);
		return BoardListResponseDto.SingleBoard.of(board);
	}

	@GetMapping("/{boardId}")
	@ResponseStatus(value = HttpStatus.OK)
	public BoardListResponseDto.SingleBoard readBoard(@PathVariable final Long boardId) {
		Board board = boardService.findById(boardId);
		return BoardListResponseDto.SingleBoard.of(board);
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public BoardListResponseDto readBoardList() {
		List<Board> boardList = boardService.findAllDesc();
		return BoardListResponseDto.of(boardList);
	}

	@PutMapping("/{boardId}")
	@ResponseStatus(value = HttpStatus.OK)
	public BoardListResponseDto.SingleBoard updateBoard(@PathVariable final Long boardId,
		@RequestBody final BoardRequestDto requestDto) {
		boardService.update(boardId, requestDto);
		Board board = boardService.findById(boardId);
		return BoardListResponseDto.SingleBoard.of(board);
	}

	@DeleteMapping("/{boardId}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deleteBoard(@PathVariable final Long boardId, @RequestParam final Long memberId) {
		boardService.delete(boardId, memberId);
		return "성공적으로 삭제되었습니다.";
	}
}
