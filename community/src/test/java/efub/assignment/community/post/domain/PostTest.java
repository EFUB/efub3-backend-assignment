package efub.assignment.community.post.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import efub.assignment.community.post.dto.PostModifyRequestDto;

public class PostTest {

	@Test
	public void updatePost_emptyTitle() {
		//given
		Post post = Post.builder().title("test post").content("test content").build();

		PostModifyRequestDto requestDto = new PostModifyRequestDto("", "new content");

		//when
		post.updatePost(requestDto);

		//then
		assertThat(post.getTitle()).isEqualTo(requestDto.getTitle());
		assertThat(post.getContent()).isEqualTo(requestDto.getContent());
		assertThat(post.getTitle()).isNotBlank();
		assertThat(post.getContent()).isNotBlank();
	}

	@Test
	public void updatePost_emptyContent() {
		//given
		Post post = Post.builder().title("test post").content("test content").build();

		PostModifyRequestDto requestDto = new PostModifyRequestDto("new title", "");

		//when
		post.updatePost(requestDto);

		//then
		assertThat(post.getTitle()).isEqualTo(requestDto.getTitle());
		assertThat(post.getContent()).isEqualTo(requestDto.getContent());
		assertThat(post.getTitle()).isNotBlank();
		assertThat(post.getContent()).isNotBlank();
	}

	@Test
	public void updatePost() {
		//given
		Post post = Post.builder().title("test post").content("test content").build();

		PostModifyRequestDto requestDto = new PostModifyRequestDto("new title", "new content");

		//when
		post.updatePost(requestDto);

		//then
		assertThat(post.getTitle()).isEqualTo(requestDto.getTitle());
		assertThat(post.getContent()).isEqualTo(requestDto.getContent());
		assertThat(post.getTitle()).isNotBlank();
		assertThat(post.getContent()).isNotBlank();
	}
}