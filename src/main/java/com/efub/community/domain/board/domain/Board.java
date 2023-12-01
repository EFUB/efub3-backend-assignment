package com.efub.community.domain.board.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.efub.community.domain.member.domain.Member;
import com.efub.community.global.common.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private Long boardId;

	@Column(name = "name", length = 30)
	private String name;

	@Column(name = "description", length = 100)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member owner;

	@Builder
	public Board(String name, String description, Member owner) {
		verifyName(name);
		this.name = name;
		verifyDescription(description);
		this.description = description;
		this.owner = owner;
	}

	private void verifyDescription(String description) {
		if (description.length() > 100 || description.length() < 10) {
			throw new IllegalArgumentException();
		}
	}

	private void verifyName(String name) {
		if (name.length() > 30 || name.length() < 3) {
			throw new IllegalArgumentException();
		}
	}

	public void updateBoard(Member member, String description) {
		this.owner = member;
		this.description = description;
	}
}
