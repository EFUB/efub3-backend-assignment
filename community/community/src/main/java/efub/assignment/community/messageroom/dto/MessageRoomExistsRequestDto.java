package efub.assignment.community.messageroom.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoomExistsRequestDto {
    private Long senderId;
    private Long receiverId;
    private Long postId;

    public MessageRoomExistsRequestDto(Long senderId, Long receiverId, Long postId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.postId = postId;
    }
}
