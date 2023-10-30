package efub.session.community.comment.domain;

import efub.session.community.account.domain.Member;
import efub.session.community.board.domain.Board;
import efub.session.community.post.domain.Post;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CommentTest {

    // fail : null content인 댓글을 작성한다.
    // builder를 사용해보았다.
    @Test
    public void failBoardTest_NULL() {

        assertThrows(IllegalArgumentException.class, () -> {
            Member writer = Member.builder()
                    .email("sjsj@ewhain.net")
                    .password("sjsj00")
                    .nickname("sj")
                    .university("ewha")
                    .studentId("2076216")
                    .build();

            Board board = Board.builder()
                    .title("test title")
                    .description("test content")
                    .pinned("test pinned")
                    .owner(writer)
                    .build();

            Post post = Post.builder()
                    .title("test post")
                    .content("test content")
                    .writer(writer)
                    .build();

            Comment comment = Comment.builder()
                    .content(null) // 주어진 조건은 nullable=false
                    .post(post)
                    .writer(writer)
                    .build();

           // testEntityManager.persist(comment);
           // testEntityManager.flush();
        });
    }
}