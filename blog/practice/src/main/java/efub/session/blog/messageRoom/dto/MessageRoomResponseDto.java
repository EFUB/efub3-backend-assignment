package efub.session.blog.messageRoom.dto;

import efub.session.blog.messageRoom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoomResponseDto {
    private Long messageRoomId;
    private Long senderId;
    private Long receiverId;
    private Long postId;
    private String firstMessage;
    private LocalDateTime createDate;

    public MessageRoomResponseDto(Long messageRoomId, Long senderId, Long receiverId, Long postId, String firstMessage, LocalDateTime createDate){
        this.messageRoomId = messageRoomId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.firstMessage = firstMessage;
        this.postId = postId;
        this.createDate = createDate;
    }

    public static MessageRoomResponseDto from(MessageRoom messageRoom){
        return new MessageRoomResponseDto(
                messageRoom.getMessageRoomId(),
                messageRoom.getSender().getMemberId(),
                messageRoom.getReceiver().getMemberId(),
                messageRoom.getPostId().getPostId(),
                messageRoom.getContent(),
                messageRoom.getCreatedDate());
    }
}
