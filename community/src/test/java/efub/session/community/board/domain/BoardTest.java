package efub.session.community.board.domain;

import efub.session.community.account.domain.Member;
import efub.session.community.account.repository.MemberRepository;
import efub.session.community.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//MemberTest와 비슷하게 구현
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class BoardTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BoardRepository boardRepository;

    // success : board 객체를 생성한다.
    @Test
    public void boardTest(){
        /* given */
        String boardName = "test";
        String description = "this is test";
        String pinned = "notice : hi this is test";
        Long memberId = 5L;

        /* when */
        Board board = new Board(boardName,description,pinned,memberRepository.findMemberByMemberId(memberId));

        /* then */
        assertThat(board.getBoardName()).isEqualTo(boardName);
        assertThat(board.getDescription()).isEqualTo(description);
        assertThat(board.getPinned()).isEqualTo(pinned);
        assertThat(board.getOwner().getMemberId()).isEqualTo(memberId);
    }

    // fail : 존재하지 않는 게시판의 owner를 변경한다.
    @Test
    public void updateBoard_GivenInvalidBoard_ReturnNullPointerException(){
        /* given */
        final Long INVALID_BOARD_ID = 10L;
        final Long VALID_MEMBER_ID = 3L;

        /* when , then */
        Board board = boardRepository.findByBoardId(INVALID_BOARD_ID);
        Member member = memberRepository.findMemberByMemberId(VALID_MEMBER_ID);
        NullPointerException e = assertThrows(NullPointerException.class ,
                ()-> board.updateBoard(member));

        assertThat(e.getMessage()).isEqualTo(null);
    }

    // fail : 게시판 소유자를 null로 변경하려는 경우
    @Test
    public void updateBoard_GivenNullOwner_ReturnIllegalArgumentException() {
        // given
        final Long VALID_BOARD_ID = 5L;

        // when , then
        Board board = boardRepository.findByBoardId(VALID_BOARD_ID);

        // 1) 게시판 소유자를 null로 변경하려고 시도한다.
        // 2) 이로 인해 'board.updateBoard(null)' 호출에서 IllegalArgumentException, 즉 에러가 발생한다.
        // 3) 이 경우, IllegalArgumentException을 예상하며 해당 예외가 발생하는지 test
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> board.updateBoard(null));
    }



}