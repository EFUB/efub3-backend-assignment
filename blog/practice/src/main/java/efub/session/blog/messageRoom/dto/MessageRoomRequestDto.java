package efub.session.blog.messageRoom.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@NotNull
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoomRequestDto {
    private Long senderId;
    private Long receiverId;
    private String firstMessage;
    private Long postId;

    @Builder
    public MessageRoomRequestDto(Long senderId, Long receiverId, String firstMessage, Long postId){
        this.senderId=senderId;
        this.receiverId=receiverId;
        this.firstMessage=firstMessage;
        this.postId=postId;
    }
}
