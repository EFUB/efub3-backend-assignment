package efub.session.community.board.controller;

import efub.session.community.board.domain.Board;
import efub.session.community.board.dto.BoardModifyRequestDto;
import efub.session.community.board.dto.BoardResponseDto;
import efub.session.community.board.dto.BoardCreateRequestDto;
import efub.session.community.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시판 생성 (POST)
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BoardResponseDto createBoard(@RequestBody @Valid final BoardCreateRequestDto requestDto) {
        return new BoardResponseDto(boardService.createBoard(requestDto));    // 새로 만든 게시판의 정보를 Dto 객체로 만들어 리턴
    }

    // 게시판 하나 조회 (GET)
    @GetMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponseDto getBoard(@PathVariable Long boardId) {
        Board findBoard = boardService.findBoardById(boardId);
        return new BoardResponseDto(findBoard);
    }

    // 게시판 주인 변경 (PATCH)
    @PatchMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponseDto update(@PathVariable final Long boardId, @RequestBody @Valid final BoardModifyRequestDto requestDto) {
        Long id = boardService.update(boardId, requestDto);
        Board findBoard = boardService.findBoardById(id);
        return new BoardResponseDto(findBoard);
    }

    // 게시판 삭제 (DELETE)
    @DeleteMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteBoard(@PathVariable Long boardId, @RequestParam Long memberId) {
        boardService.deleteBoard(boardId, memberId);
        return "성공적으로 삭제가 완료되었습니다.";
    }

}