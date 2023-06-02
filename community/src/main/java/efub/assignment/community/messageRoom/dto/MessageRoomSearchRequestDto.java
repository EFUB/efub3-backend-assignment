package efub.assignment.community.messageRoom.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageRoomSearchRequestDto {
    private Long createrId;
    private Long receiverId;
    private Long postId;
}
