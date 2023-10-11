package efub.session.blog.comment;

import efub.session.blog.board.domain.Board;
import efub.session.blog.board.service.BoardService;
import efub.session.blog.comment.domain.Comment;
import efub.session.blog.comment.service.CommentService;
import efub.session.blog.member.domain.Member;
import efub.session.blog.member.service.MemberService;
import efub.session.blog.post.domain.Post;
import efub.session.blog.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommentTest {
    private MemberService memberService;
    private BoardService boardService;
    private PostService postService;
    private CommentService commentService;
    @DisplayName("성공: comment 객체 생성")
    @Test
    public void commentTest(){
        String content="안녕하세요~";
        Long memberId=1L;
        Member writer=memberService.findMemberById(memberId);
        Long postId=1L;
        Post post = postService.findPost(postId);

        Comment comment = new Comment(content, post, writer);

        assertNotNull(comment);
    }

    @DisplayName("실패: 없는 댓글 content 수정")
    @Test
    public void update_no_exist_comment_content(){
        String content="hi";
        Long commentId=3L;
        Comment comment = commentService.findCommentById(commentId);
        NullPointerException e = assertThrows(NullPointerException.class ,
                ()-> comment.modifyComment(content));

        assertThat(e.getMessage()).isEqualTo(null);
    }
}
