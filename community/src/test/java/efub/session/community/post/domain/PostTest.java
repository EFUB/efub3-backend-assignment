package efub.session.community.post.domain;

import efub.session.community.post.dto.PostModifyRequestDto;
import efub.session.community.post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//member test code와 비슷하게 작성
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class PostTest {
    @Autowired
    public PostRepository postRepository;

    // success : 게시글 내용 수정
    @Test
    public void updatePostTest() {
        // given
        Long postId = 6L;
        Long memberId = 6L;
        String content = "test";

        // when
        PostModifyRequestDto requestDto = new PostModifyRequestDto(memberId, content);
        Post post = postRepository.findPostByPostId(postId);
        post.updatePost(requestDto);

        // then
        assertThat(post.getContent()).isEqualTo(content);
    }

    // fail : 존재하지 않는 게시글을 수정한다.
    @Test
    public void updatePost_GivenInvalidPost_ReturnNullPointerException() {
        // given
        final Long INVALID_POST_ID = 20L;
        Long memberId = 3L;
        String content = "test";

        // when , then
        PostModifyRequestDto requestDto = new PostModifyRequestDto(memberId, content);
        Post post = postRepository.findPostByPostId(INVALID_POST_ID);
        NullPointerException e = assertThrows(NullPointerException.class,
                () -> post.updatePost(requestDto));
        assertThat(e.getMessage()).isEqualTo(null);
    }

    // fail : 게시글의 내용을 null 로 수정한다.
    @Test
    public void updatePost_GivenNullContent_ReturnNull(){
        // given
        Long postId= 6L;
        Long memberId = 3L;
        String content = null;

        // when , then
        PostModifyRequestDto requestDto = new PostModifyRequestDto(memberId,content);
        Post post = postRepository.findPostByPostId(postId);
        post.updatePost(requestDto);
        assertThat(post.getContent()).isNull();
    }
}
