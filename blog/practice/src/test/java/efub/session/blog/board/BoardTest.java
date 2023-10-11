package efub.session.blog.board;

import efub.session.blog.board.domain.Board;
import efub.session.blog.board.dto.BoardModifyRequestDto;
import efub.session.blog.board.service.BoardService;
import efub.session.blog.member.domain.Member;
import efub.session.blog.member.repository.MemberRepository;
import efub.session.blog.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {
    private MemberService memberService;
    private BoardService boardService;
    @DisplayName("성공: board 객체 생성")
    @Test
    public void boardTest(){
        String name="맛집";
        String discription="맛집 추천 게시판입니다";
        String notic="사진 필수";
        Long memberId=1L;
        Member owner=memberService.findMemberById(memberId);

        Board board = new Board(name, discription, name, owner);

        assertNotNull(board);
    }

    @DisplayName("없는 게시판 이름 변경")
    @Test
    public void update_no_exist_boardName(){
        Long boardId=10L;
        String updateName="맛집2";

        Board board = boardService.findBoard(boardId);
        NullPointerException e = assertThrows(NullPointerException.class ,
                ()-> board.updateBoardName(updateName));

        assertThat(e.getMessage()).isEqualTo(null);
    }

    @DisplayName("없는 게시판 오너 변경")
    @Test
    public void update_no_exist_boardOwner(){
        Long boardId=10L;
        Long memberId=1L;
        Member owner=memberService.findMemberById(memberId);

        Board board = boardService.findBoard(boardId);
        NullPointerException e = assertThrows(NullPointerException.class ,
                ()-> board.updateBoardOwner(owner));

        assertThat(e.getMessage()).isEqualTo(null);
    }
}
