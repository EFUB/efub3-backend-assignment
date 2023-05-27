package efub.backend.assignment.message.dto;

import efub.backend.assignment.member.domain.Member;
import lombok.Getter;

/*
{
    "senderId":"1",
    "receiverId":"2",
    "messageRoomId" : "1",
    "content": "쪽지방의 첫번째 쪽지 내용"
}
 */

@Getter
public class MessageRequestDto {
    private Member senderId;
    private Member receiverId;
    private Long messageRoomId;
    private String content;

}