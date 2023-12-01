package com.efub.community.domain.chat.domain;

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

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "message_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_room_id", referencedColumnName = "message_room_id")
	private MessageRoom messageRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_id", referencedColumnName = "member_id")
	private Member sender;

	@Column(name = "content", nullable = false, length = 300)
	private String content;

	@Builder
	public Message(MessageRoom messageRoom, Member sender, String content) {
		this.messageRoom = messageRoom;
		this.sender = sender;
		this.content = content;
	}

	public void setMessageRoom(MessageRoom messageRoom) {
		if (this.messageRoom != null) {
			this.messageRoom.getMessages().remove(this);
		}
		this.messageRoom = messageRoom;
		messageRoom.getMessages().add(this);
		if (!messageRoom.getMessages().contains(this)) {
			messageRoom.getMessages().add(this);
		}
	}
}
