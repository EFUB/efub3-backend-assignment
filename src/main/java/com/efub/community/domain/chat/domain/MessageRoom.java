package com.efub.community.domain.chat.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.efub.community.domain.board.domain.Post;
import com.efub.community.domain.member.domain.Member;
import com.efub.community.global.common.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoom extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_room_id")
	private Long messageRoomId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "initial_sender_id", referencedColumnName = "member_id")
	private Member initialSender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "initial_receiver_id", referencedColumnName = "member_id")
	private Member initialReceiver;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_from", referencedColumnName = "post_id")
	private Post createdFrom;

	@OneToMany(mappedBy = "messageRoom")
	private List<Message> messages = new ArrayList<>();

	@Builder
	public MessageRoom(Member initialSender, Member initialReceiver, Post createdFrom, List<Message> messages) {
		this.initialSender = initialSender;
		this.initialReceiver = initialReceiver;
		this.createdFrom = createdFrom;
		this.messages = messages;
	}

	public void addMessage(Message message) {
		message.setMessageRoom(this);
	}

	public Message getLatestMessage() {
		return messages.stream()
			.max(Comparator.comparing(BaseTimeEntity::getCreatedDate))
			.orElseThrow(() -> new RuntimeException("No messages found in the message room"));
	}
}
