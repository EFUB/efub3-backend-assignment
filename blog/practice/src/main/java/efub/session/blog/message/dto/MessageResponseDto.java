package efub.session.blog.message.dto;

import efub.session.blog.message.domain.Message;
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
    private String content;
    private LocalDateTime createDate;

    public MessageResponseDto(Long messageId, Long messageRoomId, Long senderId, Long receiverId, String content,
                              LocalDateTime createDate) {
        this.messageId = messageId;
        this.messageRoomId = messageRoomId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.createDate = createDate;
    }

    public static MessageResponseDto from(Message message) {
        return new MessageResponseDto(
                message.getMessageId(),
                message.getMessageRoomId().getMessageRoomId(),
                message.getSender().getMemberId(),
                message.getReceiver().getMemberId(),
                message.getContent(),
                message.getCreatedDate()
        );
    }
}
