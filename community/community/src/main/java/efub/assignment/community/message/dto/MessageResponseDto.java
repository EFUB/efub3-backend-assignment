package efub.assignment.community.message.dto;

import efub.assignment.community.message.domain.Message;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageResponseDto {
    private Long messageId;
    private Long messageRoomId;
    private Long senderId;
    private Long receiverId;
    private String message;
    private LocalDateTime createdDate;

    public MessageResponseDto(Long messageId, Long messageRoomId, Long senderId, Long receiverId, String message,
                              LocalDateTime createdDate) {
        this.messageId = messageId;
        this.messageRoomId = messageRoomId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.createdDate = createdDate;
    }

    public static MessageResponseDto from(Message message) {
        return new MessageResponseDto(
                message.getMessageId(),
                message.getMessageRoomId().getMessageRoomId(),
                message.getSender().getMemberId(),
                message.getReceiver().getMemberId(),
                message.getMessage(),
                message.getCreatedDate()
        );
    }
}
