package efub.assignment.community.comment.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import efub.assignment.community.comment.dto.CommentModifyRequestDto;

public class CommentTest {

	@Test
	public void modifyComment_emptyContent() {
		//given
		Comment comment = Comment.builder().content("test").build();

		CommentModifyRequestDto requestDto = CommentModifyRequestDto.builder().content("").build();

		//when
		comment.modify(requestDto);

		//then
		assertThat(comment.getContent()).isEqualTo(requestDto.getContent());
		assertThat(comment.getContent()).isNotBlank();
	}

	@Test
	public void modifyComment_noChange() {
		//given
		String message = "test";
		Comment comment = Comment.builder().content(message).build();

		CommentModifyRequestDto requestDto = CommentModifyRequestDto.builder().content(message).build();

		//when
		comment.modify(requestDto);

		//then
		assertThat(requestDto.getContent()).isNotEqualTo(message);
		assertThat(comment.getContent()).isEqualTo(requestDto.getContent());
		assertThat(comment.getContent()).isNotBlank();
	}

	@Test
	public void modify() {
		//given
		Comment comment = Comment.builder().content("test").build();

		CommentModifyRequestDto requestDto = CommentModifyRequestDto.builder().content("new").build();

		//when
		comment.modify(requestDto);

		//then
		assertThat(comment.getContent()).isEqualTo(requestDto.getContent());
		assertThat(comment.getContent()).isNotBlank();
	}
}