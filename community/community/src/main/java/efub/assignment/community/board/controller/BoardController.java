package efub.assignment.community.board.controller;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.BoardModifyRequestDto;
import efub.assignment.community.board.dto.BoardRequestDto;
import efub.assignment.community.board.dto.BoardResponseDto;
import efub.assignment.community.board.service.BoardService;
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

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BoardResponseDto boardAdd(@RequestBody BoardRequestDto requestDto) {
        Board board = boardService.addBoard(requestDto);
        return new BoardResponseDto(board);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<BoardResponseDto> boardListFind() {
        List<Board> boardList = boardService.findBoardList();
        List<BoardResponseDto> responseDtoList = new ArrayList<>();

        for (Board board : boardList) {
            responseDtoList.add(new BoardResponseDto(board));
        }
        return responseDtoList;
    }

    @GetMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponseDto boardFind(@PathVariable Long boardId) {
        Board board = boardService.findBoard(boardId);
        return new BoardResponseDto(board);
    }

    @PutMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponseDto boardModify(@PathVariable Long boardId, @RequestBody BoardModifyRequestDto requestDto) {
        Board board = boardService.modifyBoard(boardId, requestDto);
        return new BoardResponseDto(board);
    }

    @DeleteMapping("/{boardId}/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String BoardRemove(@PathVariable Long boardId, @RequestBody Long memberId) {
        boardService.removeBoard(boardId, memberId);
        return "성공적으로 삭제되었습니다.";
    }
}
