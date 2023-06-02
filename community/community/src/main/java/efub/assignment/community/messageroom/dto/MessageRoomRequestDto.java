package efub.assignment.community.messageroom.dto;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.messageroom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoomRequestDto {
    private Long senderId;
    private Long receiverId;
    private String firstMessage;
    private Long postId;
}
