package com.efub.community.domain.board.domain;

import org.junit.jupiter.api.Test;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.member.domain.Member;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BoardTest {

	@Test
	void boardCreateTest(){
		String name="오늘 날씨";
		String description="오늘 날씨 완전 시원하당";
		Member owner = new Member("test@gmail.com","encodedPassword","nickname",1,"ewha");

		Board board = createBoard(name,description,owner);

		assertThat(board.getName()).isEqualTo(name);
		assertThat(board.getDescription()).isEqualTo(description);
		assertThat(board.getOwner()).isEqualTo(owner);

	}

	private Board createBoard(String name, String description, Member owner){
		Board board=Board.builder()
			.name(name)
			.description(description)
			.owner(owner)
			.build();
		return board;
	}
}
