package com.efub.community.domain.board.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.efub.community.domain.board.BoardDomainTest;
import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.member.repository.MemberRepository;

@DataJpaTest
class BoardRepositoryTest extends BoardDomainTest {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	@DisplayName("게시판을 저장하고 아이디로 조회합니다.")
	void saveAndFindById() {
		// given
		// when
		Board savedBoard = boardRepository.save(board1);
		Optional<Board> optionalBoard = boardRepository.findById(savedBoard.getBoardId());

		// then
		softly.assertThat(optionalBoard).isNotEmpty();
		Board foundBoard = optionalBoard.get();
		softly.assertThat(foundBoard.getDescription()).isEqualTo(description);
		softly.assertThat(foundBoard.getOwner()).isEqualTo(member1);
	}

	@Test
	@DisplayName("게시판의 전체 목록을 내림차순으로 조회합니다.")
	void findAllByOrderByBoardIdDesc() {
		// given
		memberRepository.save(member1);
		memberRepository.save(member2);
		boardRepository.save(board1);
		boardRepository.save(board2);

		// when
		List<Board> boardList = boardRepository.findAllByOrderByBoardIdDesc();

		// then
		softly.assertThat(boardList).hasSize(2);
		softly.assertThat(boardList.get(0).getBoardId()).isGreaterThan(boardList.get(1).getBoardId());
	}

	@Test
	@DisplayName("이름으로 게시판의 존재 여부를 확인합니다.")
	void existsByName() {
		// given
		memberRepository.save(member1);
		boardRepository.save(board1);

		// when
		boolean trueExist = boardRepository.existsByName(board1.getName());
		boolean falseExist = boardRepository.existsByName(board2.getName());

		// then
		softly.assertThat(trueExist).isTrue();
		softly.assertThat(falseExist).isFalse();
	}
}
