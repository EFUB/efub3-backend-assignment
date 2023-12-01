package com.efub.community.domain.board.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.efub.community.domain.board.domain.Board;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardListResponseDto {

	private List<BoardListResponseDto.SingleBoard> boards;
	private Integer count;

	public static BoardListResponseDto of(List<Board> boardList) {
		return BoardListResponseDto.builder()
			.boards(boardList.stream().map(BoardListResponseDto.SingleBoard::of).collect(Collectors.toList()))
			.count(boardList.size())
			.build();
	}

	@Getter
	public static class SingleBoard {
		private final Long boardId;
		private final String boardName;
		private final String description;
		private final Long ownerId;
		private final LocalDateTime createdDate;
		private final LocalDateTime modifiedDate;

		public SingleBoard(Board board) {
			this.boardId = board.getBoardId();
			this.boardName = board.getName();
			this.ownerId = board.getOwner().getMemberId();
			this.description = board.getDescription();
			this.createdDate = board.getCreatedDate();
			this.modifiedDate = board.getModifiedDate();
		}

		public static BoardListResponseDto.SingleBoard of(Board board) {
			return new BoardListResponseDto.SingleBoard(board);
		}
	}
}
