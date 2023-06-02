package efub.backend.assignment.message.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
{
    "senderId":"1",
    "receiverId":"2",
    "messageRoomId" : "1",
    "content": "쪽지방의 첫번째 쪽지 내용"
}
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRequestDto {
    private Long senderId;
    private Long receiverId;
    private Long messageRoomId;
    private String content;
}