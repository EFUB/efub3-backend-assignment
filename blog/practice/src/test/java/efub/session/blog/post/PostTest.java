package efub.session.blog.post;

import efub.session.blog.board.domain.Board;
import efub.session.blog.board.service.BoardService;
import efub.session.blog.member.domain.Member;
import efub.session.blog.member.service.MemberService;
import efub.session.blog.post.domain.Post;
import efub.session.blog.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PostTest {
    private MemberService memberService;
    private BoardService boardService;
    private PostService postService;
    @DisplayName("성공: post 객체 생성")
    @Test
    public void postTest(){
        Boolean anonymouse=true;
        String content="안녕하세요~";
        Long memberId=1L;
        Member member=memberService.findMemberById(memberId);
        Long boardId=1L;
        Board board=boardService.findBoard(boardId);

        Post post = new Post(anonymouse,content,member,board);

        assertNotNull(post);
    }

    @DisplayName("실패: 없는 게시글 content 수정")
    @Test
    public void update_no_exist_post_content(){
        String content="hi";
        Long postId=3L;

        Post post = postService.findPost(postId);
        NullPointerException e = assertThrows(NullPointerException.class ,
                ()-> post.updatePostContent(content));

        assertThat(e.getMessage()).isEqualTo(null);
    }

    @DisplayName("실패: 없는 게시글 owner 수정")
    @Test
    public void update_no_exist_post_owner(){
        Long memberId=1L;
        Member member=memberService.findMemberById(memberId);
        Long postId=3L;

        Post post = postService.findPost(postId);
        NullPointerException e = assertThrows(NullPointerException.class ,
                ()-> post.updatePostOwner(member));

        assertThat(e.getMessage()).isEqualTo(null);
    }
}
