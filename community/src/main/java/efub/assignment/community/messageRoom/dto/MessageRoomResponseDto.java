package efub.assignment.community.messageRoom.dto;

import efub.assignment.community.message.domain.Message;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class MessageRoomResponseDto {
    private Long messageRoomId;
    private String latestContent;
    private LocalDateTime latestDate;

    public MessageRoomResponseDto(MessageRoom messageRoom) {
        List<Message> messageList = messageRoom.getMessageList();
        Message latestMsg;
        if (messageList.isEmpty())
            latestMsg = null;
        else
            latestMsg = messageList.get(messageList.size() - 1);

        this.messageRoomId = messageRoom.getMessageRoomId();
        this.latestContent = latestMsg.getContent();
        this.latestDate = latestMsg.getCreatedDate();
    }
}
