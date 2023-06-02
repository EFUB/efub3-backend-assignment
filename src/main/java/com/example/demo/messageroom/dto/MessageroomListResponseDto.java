package com.example.demo.messageroom.dto;

import com.example.demo.messageroom.domain.Messageroom;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageroomListResponseDto {

    // 쪽지방 리스트 자체의 필드
    private List<MessageroomListResponseDto.SingleRoom> messageroomList;    // 쪽지방들
    private Integer messagerommCount;   // 쪽지방의 수

    // 쪽지방 하나에 대한 클래스
    /*
    [messageRoom id, 최신 메시지 1개, 최신 메시지 보낸 시각]
     */
    @Getter
    public static class SingleRoom {
        private Long messageroomId;
        private String recentMessage;
        private LocalDateTime recentMessageDate;

        public SingleRoom (Messageroom room) {
            this.messageroomId = room.getMessageroomId();

        }
    }
}
