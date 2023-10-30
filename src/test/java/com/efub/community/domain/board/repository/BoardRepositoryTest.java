package com.efub.community.domain.board.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.member.domain.Member;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE, connection = EmbeddedDatabaseConnection.H2)
public class BoardRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;
	@Autowired
	private BoardRepository boardRepository;

	@Test
	void saveBoardSearchTest(){
		String name1="오늘 날씨";
		String description1="오늘 날씨 완전 시원하당";
		Member owner1 = new Member("test@gmail.com","encodedPassword","nickname",1,"ewha");
		testEntityManager.persist(owner1);
		Board board1=Board.builder()
			.name(name1)
			.description(description1)
			.owner(owner1)
			.build();
		String name2="오늘 날씨2";
		String description2="오늘 날씨 완전 시원하당2";
		Member owner2 = new Member("test2@gmail.com","encodedPassword","nickname",1,"ewha");
		testEntityManager.persist(owner2);
		Board board2=Board.builder()
			.name(name2)
			.description(description2)
			.owner(owner2)
			.build();

		testEntityManager.persist(board1);
		testEntityManager.persist(board2);

		List<Board> boardList = boardRepository.findAll();

		assertThat(boardList).hasSize(2);
		assertThat(boardList).contains(board1, board2);

	}

}
