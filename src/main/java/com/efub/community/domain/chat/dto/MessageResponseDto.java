package com.efub.community.domain.chat.dto;

import java.time.LocalDateTime;

import com.efub.community.domain.chat.domain.Message;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageResponseDto {
	
	private Long senderId;
	private Long messageRoomId;
	private String message;
	private LocalDateTime createdDate;

	public MessageResponseDto(Message message) {
		this.senderId = message.getSender().getMemberId();
		this.messageRoomId = message.getMessageRoom().getMessageRoomId();
		this.message = message.getContent();
		this.createdDate = message.getCreatedDate();
	}
}
