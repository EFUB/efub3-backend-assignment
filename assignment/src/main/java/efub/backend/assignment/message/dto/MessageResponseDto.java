package efub.backend.assignment.message.dto;

import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.message.domain.Message;
import efub.backend.assignment.messageroom.domain.MessageRoom;
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
    private Member senderId;
    private Member receiverId;
    private Long messageId;
    private String content;
    private LocalDateTime createdDate;

    public MessageResponseDto(Member senderId, Member receiverId, Long messageId, String content, LocalDateTime createdDate) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageId = messageId;
        this.content = content;
        this.createdDate = createdDate;
    }

    public static efub.backend.assignment.message.dto.MessageResponseDto from(Message message) {
        return new efub.backend.assignment.message.dto.MessageResponseDto(
                message.getSenderId(),
                message.getReceiverId(),
                message.getMessageId(),
                message.getContent(),
                message.getCreatedDate());
    }
}