package efub.backend.assignment.messageroom.dto;

/*
{
    "roomId" : 1,
    "senderId" : 1,
    "receiverId" : 2,
    "messsgeContent" : "첫번째 쪽지",
    "createdDated" : "2023-05-27T12:50:12.666667"
}
 */

import efub.backend.assignment.messageroom.domain.MessageRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@NoArgsConstructor
public class MessageRoomResponseDto {
    private Long roomId;
    private Long senderId;
    private Long receiverId;
    private String messageContent;
    private LocalDateTime createdDate;

    public MessageRoomResponseDto(Long roomId, Long senderId, Long receiverId, String messageContent, LocalDateTime createdDate) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageContent = messageContent;
        this.createdDate = createdDate;
    }

    public MessageRoomResponseDto(MessageRoom messageRoom){
        this.roomId = messageRoom.getRoomId();
        this.senderId = messageRoom.getSenderId().getMemberId();
        this.receiverId = messageRoom.getReceiverId().getMemberId();
        this.messageContent = messageRoom.getMessageContent();
        this.createdDate = messageRoom.getCreatedDate();
    }
}