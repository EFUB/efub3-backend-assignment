package efub.assignment.community.message.dto;

import efub.assignment.community.message.domain.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MessageCreateResponseDto {
    private Long messageRoomId;
    private Long senderId;
    private String content;
    private LocalDateTime createdDate;

    public MessageCreateResponseDto(Message message) {
        this.messageRoomId = message.getMessageRoom().getMessageRoomId();
        this.senderId = message.getSender().getMemberId();
        this.content = message.getContent();
        this.createdDate = message.getCreatedDate();
    }
}
