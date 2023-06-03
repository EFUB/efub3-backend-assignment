package efub.backend.assignment.messageroom.dto;

import efub.backend.assignment.messageroom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MessageRoomListResponseDto {
    private List<MessageRoomListResponseDto.SingleMessageRoom> messageRooms;
    private Integer count;

    @Getter
    public static class SingleMessageRoom{
        private Long roomId;
        private String message;
        private LocalDateTime createdDate;

        public SingleMessageRoom(MessageRoom messageRoom) {
            this.roomId = messageRoom.getRoomId();
            this.createdDate = messageRoom.getCreatedDate();
        }

        public static MessageRoomListResponseDto.SingleMessageRoom of(MessageRoom messageRoom){
            return new MessageRoomListResponseDto.SingleMessageRoom(messageRoom);
        }
    }

    public static MessageRoomListResponseDto of(List<MessageRoom> messageRoomList){
            return MessageRoomListResponseDto.builder()
                    .messageRooms(messageRoomList.stream().map(MessageRoomListResponseDto.SingleMessageRoom::of).collect(Collectors.toList()))
                    .count(messageRoomList.size())
                    .build();
    }
}
