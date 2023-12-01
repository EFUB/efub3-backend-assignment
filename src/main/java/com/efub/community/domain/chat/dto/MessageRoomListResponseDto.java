package com.efub.community.domain.chat.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.efub.community.domain.chat.domain.Message;
import com.efub.community.domain.chat.domain.MessageRoom;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageRoomListResponseDto {

	private List<MessageRoomListResponseDto.SingleMessageRoom> messageRooms;
	private Integer count;

	public static MessageRoomListResponseDto of(List<MessageRoom> messageRoomList) {
		return MessageRoomListResponseDto.builder()
			.messageRooms(messageRoomList.stream()
				.map(MessageRoomListResponseDto.SingleMessageRoom::of)
				.collect(Collectors.toList()))
			.count(messageRoomList.size())
			.build();
	}

	@Getter
	public static class SingleMessageRoom {

		private final Long messageRoomId;
		private final String message;
		private final LocalDateTime createdDate;

		public SingleMessageRoom(MessageRoom messageRoom) {
			this.messageRoomId = messageRoom.getMessageRoomId();
			Message latestMessage = messageRoom.getLatestMessage();
			this.message = latestMessage.getContent();
			this.createdDate = latestMessage.getCreatedDate();
		}

		public static MessageRoomListResponseDto.SingleMessageRoom of(MessageRoom messageRoom) {
			return new MessageRoomListResponseDto.SingleMessageRoom(messageRoom);
		}
	}
}
