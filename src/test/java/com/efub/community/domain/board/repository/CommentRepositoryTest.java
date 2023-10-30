package com.efub.community.domain.board.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.board.domain.Comment;
import com.efub.community.domain.board.domain.Post;
import com.efub.community.domain.member.domain.Member;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE, connection = EmbeddedDatabaseConnection.H2)
public class CommentRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;
	@Autowired
	private CommentRepository commentRepository;

	@Test
	void saveAndDeleteCommentTest(){
		String content="content";
		Member writer= new Member("test@gmail.com","encodedPassword","nickname",1,"ewha");
		testEntityManager.persist(writer);
		Board board=new Board("오늘 날씨","오늘 날씨 완전 시원하당", writer);
		testEntityManager.persist(board);
		boolean anonymous=false;
		Post post= new Post(content, writer, anonymous, board);
		testEntityManager.persist(post);

		Comment comment = Comment.builder()
			.content(content)
			.writer(writer)
			.post(post)
			.anonymous(anonymous)
			.build();
		testEntityManager.persist(comment);

		commentRepository.deleteAll();
		assertThat(commentRepository.findAll()).isEmpty();
	}


}
