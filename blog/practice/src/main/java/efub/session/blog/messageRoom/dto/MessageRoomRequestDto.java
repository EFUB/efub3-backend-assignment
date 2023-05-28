package efub.session.blog.messageRoom.dto;

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
