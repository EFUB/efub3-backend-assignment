package com.example.demo.messageroom.dto;

import com.example.demo.messageroom.domain.Messageroom;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageroomResponseDto {

    private Long messageroomId;
    private Long senderId;
    private Long receiverId;
    private String firstMessage;
    private LocalDateTime createdDate;

    public static MessageroomResponseDto of(Messageroom room) {
        return MessageroomResponseDto.builder()
                .messageroomId(room.getMessageroomId())
                .senderId(room.getSender().getMemberId())
                .receiverId(room.getReceiver().getMemberId())
                .firstMessage(room.getFirstMessage())
                .createdDate(room.getCreatedDate())
                .build();
    }
}
