package efub.session.blog.board.controller;

import efub.session.blog.board.domain.Board;
import efub.session.blog.board.dto.BoardModifyRequestDto;
import efub.session.blog.board.dto.BoardRequestDto;
import efub.session.blog.board.dto.BoardResponseDto;
import efub.session.blog.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //보드 생성
    @PostMapping
    @ResponseStatus(value=HttpStatus.CREATED)
    public BoardResponseDto boardAdd(@RequestBody @Valid final BoardRequestDto requestDto){
        Board board = boardService.addBoard(requestDto);
        return new BoardResponseDto(board);
    }

    //보드 목록 조회
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<BoardResponseDto> boardListFind(){
        List<Board> boardList=boardService.findBoardList();
        List<BoardResponseDto> responseDtoList = new ArrayList<>();

        for(Board board:boardList){
            responseDtoList.add(new BoardResponseDto(board));
        }
        return responseDtoList;
    }

    //보드 조회
    @GetMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponseDto boardFind(@PathVariable Long boardId){
        Board board = boardService.findBoard(boardId);
        return new BoardResponseDto(board);
    }

    //보드 삭제
    @DeleteMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String boardRemove(@PathVariable Long boardId,@RequestParam Long memberId){
        boardService.removeBoard(boardId,memberId);
        return "성공적으로 삭제되었습니다.";
    }

    //보드 수정
    @PutMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponseDto boardModify(@PathVariable Long boardId,@RequestBody @Valid final BoardModifyRequestDto requestDto){
        Board board = boardService.modifyBoard(boardId,requestDto);
        return new BoardResponseDto(board);
    }

}
