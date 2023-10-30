package efub.session.community.comment.controller;

import efub.session.community.comment.domain.Comment;
import efub.session.community.comment.dto.CommentModifyRequestDto;
import efub.session.community.comment.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


// Spring Boot는 테스트용 데이터베이스를 자동으로 구성하지 않는다.
// 대신, 프로덕션 데이터베이스 설정을 그대로 사용한다.
// 테스트에서 실제 데이터베이스를 사용하고자 할 때 유용하다.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentControllerTest {
    @Autowired
    public CommentRepository commentRepository;

    // success : comment를 수정한다.
    @Test
    public void updateCommentTest(){
        //given
        Long commentId = 1L;
        Long memberId = 1L;
        String content = "test";

        // when
        CommentModifyRequestDto requestDto = new CommentModifyRequestDto(memberId,content);
        Comment comment = commentRepository.findCommentByCommentId(commentId);
        comment.updateComment(String.valueOf(requestDto));

        // then
        assertThat(comment.getContent()).isEqualTo(content);
    }

    // failed : 존재하지 않는 댓글을 수정한다.
    @Test
    public void updateComment_GivenInvalidCommentId_ReturnNullPointerException(){
        // given
        Long INVALID_COMMENT_ID= 10L;
        Long memberId = 1L;
        String content = "test";

        // when , then
        CommentModifyRequestDto requestDto = new CommentModifyRequestDto(memberId,content);
        Comment comment = commentRepository.findCommentByCommentId(INVALID_COMMENT_ID);

        NullPointerException e = assertThrows(NullPointerException.class ,
                ()-> comment.updateComment(String.valueOf(requestDto)));
        assertThat(e.getMessage()).isEqualTo(null);
    }



}