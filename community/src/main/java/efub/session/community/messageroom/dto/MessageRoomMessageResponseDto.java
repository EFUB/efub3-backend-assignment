package efub.session.community.messageroom.dto;

import efub.session.community.message.domain.Message;
import efub.session.community.message.dto.MessageFindRequestDto;
import efub.session.community.messageroom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MessageRoomMessageResponseDto {
    private Long messageRoomId;
    private Long otherMemberId; // 상대방 id
    private List<MessageRoomMessage> messageRoomMassages; // 메시지 목록 =  메세지 내용, 시각, 수신/송신 인지

    public static MessageRoomMessageResponseDto of(Long messageRoomId, Long otherUserId, List<MessageFindRequestDto> requestDtoList) {
        return MessageRoomMessageResponseDto.builder()
                .messageRoomId(messageRoomId)
                .otherMemberId(otherUserId)
                .messageRoomMassages(requestDtoList.stream().map(MessageRoomMessage::of).collect(Collectors.toList()))
                .build();
    }

    @Getter
    public static class MessageRoomMessage{
        private String messageContent;
        private LocalDateTime messageCreated;
        private Boolean isReceived; // 수신/송신 인지

        public MessageRoomMessage(MessageFindRequestDto requestDto){
            this.messageContent= requestDto.getMessageContent();
            this.messageCreated=requestDto.getMessageCreated();
            this.isReceived=requestDto.getIsReceived();
        }

        private static MessageRoomMessage of(MessageFindRequestDto requestDto){
            return new MessageRoomMessage(requestDto);
        }
    }
}
