package efub.backend.assignment.messageroom.dto;

/*
{
    "senderId" : 1,
    "receiverId" : 2,
    "messsgeContent" : "첫번째 쪽지",
    "messageId" : 1
}
 */

import efub.backend.assignment.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MessageRoomRequestDto {
    private Member senderId;
    private Member receiverId;
    private String messageContent;
    private Long messageId;
}