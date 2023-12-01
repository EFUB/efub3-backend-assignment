package com.efub.community.domain.board.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.board.dto.request.BoardRequestDto;
import com.efub.community.domain.board.repository.BoardRepository;
import com.efub.community.domain.member.domain.Member;
import com.efub.community.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final MemberService memberService;

	public Long create(BoardRequestDto requestDto) {
		if (isExistedBoardName(requestDto.getName())) {
			throw new IllegalArgumentException("이미 존재하는 게시판 이름입니다. " + requestDto.getName());
		}
		Member member = memberService.findById(requestDto.getMemberId());
		Board board = boardRepository.save(requestDto.toEntity(member));
		return board.getBoardId();
	}

	public void update(Long boardId, BoardRequestDto requestDto) {
		Board board = findById(boardId);
		checkValidMember(requestDto.getMemberId(), board.getOwner().getMemberId());
		Member member = memberService.findById(requestDto.getMemberId());
		board.updateBoard(member, requestDto.getDescription());
	}

	public void delete(Long boardId, Long memberId) {
		Board board = findById(boardId);
		checkValidMember(memberId, board.getOwner().getMemberId());
		boardRepository.delete(board);
	}

	private void checkValidMember(Long currentMemberId, Long tagetMemberId) {
		if (!Objects.equals(currentMemberId, tagetMemberId)) {
			throw new IllegalArgumentException();
		}
	}

	@Transactional(readOnly = true)
	public Board findById(Long boardId) {
		return boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException("해당 게시판이 없습니다. id=" + boardId));
	}

	@Transactional(readOnly = true)
	public List<Board> findAllDesc() {
		return boardRepository.findAllByOrderByBoardIdDesc();
	}

	@Transactional(readOnly = true)
	public boolean isExistedBoardName(String boardName) {
		return boardRepository.existsByName(boardName);
	}
}
