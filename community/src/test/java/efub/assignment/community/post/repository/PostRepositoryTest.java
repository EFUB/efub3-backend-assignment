package efub.assignment.community.post.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import efub.assignment.community.post.domain.Post;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest {

	@Autowired
	private PostRepository postRepository;

	@Test
	public void save() {
		//given
		Post post = Post.builder().title("test post").content("test content").build();

		//when
		Post savedPost = postRepository.save(post);

		//then
		assertThat(savedPost.getTitle()).isEqualTo("test post");
		assertThat(savedPost.getContent()).isEqualTo("test content");
	}
}