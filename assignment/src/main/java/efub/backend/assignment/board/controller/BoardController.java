package efub.backend.assignment.board.controller;


import efub.backend.assignment.board.domain.Board;
import efub.backend.assignment.board.dto.BoardModifyRequestDto;
import efub.backend.assignment.board.dto.BoardRequestDto;
import efub.backend.assignment.board.dto.BoardResponseDto;
import efub.backend.assignment.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController // @Controller와 @ResponseBody의 기능을 동시에 사용 → 해당 컨트롤러가 리턴하는 객체는 JSON 형태로 전달됨
@RequestMapping("/boards") // 경로 맵핑
@RequiredArgsConstructor // lombok
public class BoardController {

    private final BoardService boardService;

    @PostMapping // HTTP 전송 방식에 따라, 해당 메소드의 방식을 지정, 여기서는 Post
    @ResponseStatus(value = HttpStatus.CREATED) // 반환하는 객체의 HTTP 응답의 상태 코드: created
    public BoardResponseDto boardAdd(@RequestBody BoardRequestDto requestDto){
        // @RequestBody: HTTP 요청이 JSON임을 명시
        Board board = boardService.addBoard(requestDto);
        return new BoardResponseDto(board);
    }

    @GetMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponseDto boardFind(@PathVariable Long boardId) {
        // @PathVariable: URL 경로의 일부를 변수로 삼아서 처리하기 위해서 사용
        Board board = boardService.findBoard(boardId);
        return new BoardResponseDto(board);
    }

    @DeleteMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String boardRemove(@PathVariable Long boardId){
        // @RequestParam: Request에 있는 특정한 이름의 데이터를 파라미터로 받아서 처리하는 경우에 사용
        boardService.removeBoard(boardId);
        return "성공적으로 삭제되었습니다.";
    }

    @PutMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponseDto boardModify(@PathVariable Long boardId, @RequestBody BoardModifyRequestDto requestDto){
        // @PathVariable: URL 경로의 일부를 변수로 삼아서 처리하기 위해서 사용
        // @RequestBody: HTTP 요청이 JSON임을 명시
        Board board = boardService.modifyBoard(boardId, requestDto);
        return new BoardResponseDto(board);
    }

}