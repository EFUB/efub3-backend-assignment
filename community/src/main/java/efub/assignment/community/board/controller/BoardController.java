package efub.assignment.community.board.controller;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.BoardCreateRequestDto;
import efub.assignment.community.board.dto.BoardResponseDto;
import efub.assignment.community.board.dto.BoardUpdateRequestDto;
import efub.assignment.community.board.service.BoardService;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.dto.PostResponseDto;
import efub.assignment.community.post.service.PostService;
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
    public BoardResponseDto boardAdd(@RequestBody BoardCreateRequestDto requestDto){
        Board board = boardService.create(requestDto);
        return new BoardResponseDto(board);
    }

    @GetMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponseDto boardFind(@PathVariable Long boardId){
        Board board = boardService.read(boardId);
        return new BoardResponseDto(board);
    }

    // 게시판에 속한 글들을 모두 조회
    @GetMapping("/{boardId}/posts")
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostResponseDto> postsListInBoard(@PathVariable Long boardId){

        List<Post> postList = postService.findPostsInBoard(boardId);
        List<PostResponseDto> responseDtoList = new ArrayList<>();

        for(Post post : postList){
            responseDtoList.add(new PostResponseDto(post));
        }
        return responseDtoList;

    }

    @PutMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponseDto boardModifiy(@PathVariable Long boardId, @RequestBody BoardUpdateRequestDto requestDto){
        Board board = boardService.update(boardId, requestDto);
        return new BoardResponseDto(board);
    }

    @DeleteMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String boardRemove(@PathVariable Long boardId){
        // 게시판에 속한 글들도 모두 삭제
        List<Post> postList = postService.findPostsInBoard(boardId);
        for (Post p: postList) {
            postService.removePost(p.getPostId());
        }

        boardService.delete(boardId);
        return "성공적으로 삭제되었습니다.";
    }
}
