package efub.session.blog.messageRoom.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoomExistRequestDto {
    private Long senderId;
    private Long receiverId;
    private Long postId;

    public MessageRoomExistRequestDto(Long senderId, Long receiverId, Long postId){
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.postId = postId;
    }
}
