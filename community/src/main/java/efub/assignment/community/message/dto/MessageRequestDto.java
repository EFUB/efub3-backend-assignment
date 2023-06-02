package efub.assignment.community.message.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageRequestDto {
    private Long messageRoomId;
    private Long senderId;
    private String content;
}
