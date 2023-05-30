package efub.session.blog.message.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRequestDto {
    private Long messageRoomId;
    private String content;
    private Long senderId;
    private Long receiverId;

    @Builder
    public MessageRequestDto(Long messageRoomId, String content, Long senderId, Long receiverId){
        this.messageRoomId=messageRoomId;
        this.content=content;
        this.senderId=senderId;
        this.receiverId=receiverId;
    }
}
