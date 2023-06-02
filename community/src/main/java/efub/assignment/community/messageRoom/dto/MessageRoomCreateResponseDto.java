package efub.assignment.community.messageRoom.dto;

import efub.assignment.community.messageRoom.domain.MessageRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MessageRoomCreateResponseDto {
    private Long messageRoomId;
    private Long createrId;
    private Long receiverId;
    private String content;
    private LocalDateTime createdDate;

    public MessageRoomCreateResponseDto(MessageRoom messageRoom){
        this.messageRoomId = messageRoom.getMessageRoomId();
        this.createrId = messageRoom.getCreater().getMemberId();
        this.receiverId = messageRoom.getReceiver().getMemberId();
        this.content = messageRoom.getContent();
        this.createdDate = messageRoom.getCreatedDate();
    }
}
