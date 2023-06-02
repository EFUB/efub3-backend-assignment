package efub.assignment.community.message.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRequestDto {
    private Long messageRoomId;
    private Long senderId;
    private Long receiverId;
    private String message;
}
