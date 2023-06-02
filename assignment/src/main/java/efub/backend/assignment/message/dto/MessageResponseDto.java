package efub.backend.assignment.message.dto;

import efub.backend.assignment.message.domain.Message;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
{
    "senderId":"1",
    "receiverId":"2",
    "messageId" : "1",
    "content": "쪽지방의 첫번째 쪽지 내용",
    "createdDate": "2023-05-27T12:50:12.666667"
}
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageResponseDto {
    private Long senderId;
    private Long receiverId;
    private Long messageId;
    private Long messageRoomId;
    private String content;
    private LocalDateTime createdDate;

    public MessageResponseDto(Long senderId, Long receiverId, Long messageId, Long messageRoomId, String content
    , LocalDateTime createdDate){
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageId = messageId;
        this.messageRoomId = messageRoomId;
        this.content = content;
        this.createdDate = createdDate;
    }

    public static MessageResponseDto from(Message message) {
        return new MessageResponseDto(
                message.getSender().getMemberId(),
                message.getReceiver().getMemberId(),
                message.getMessageId(),
                message.getMessageRoom().getRoomId(),
                message.getContent(),
                message.getCreatedDate()
        );
    }
}