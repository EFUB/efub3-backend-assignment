package efub.assignment.community.messageroom.dto;

import efub.assignment.community.messageroom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoomResponseDto {
    private Long messageRoomId;
    private Long sender;
    private Long receiver;
    private String firstMessage;
    private Long postId;
    private LocalDateTime createdDate;

    public MessageRoomResponseDto(Long messageRoomId, Long sender, Long receiver, String firstMessage,
                                  Long postId, LocalDateTime createdDate) {
        this.messageRoomId = messageRoomId;
        this.sender = sender;
        this.receiver = receiver;
        this.firstMessage = firstMessage;
        this.postId = postId;
        this.createdDate = createdDate;
    }

    public static MessageRoomResponseDto from(MessageRoom messageRoom) {
        return new MessageRoomResponseDto(
                messageRoom.getMessageRoomId(),
                messageRoom.getSender().getMemberId(),
                messageRoom.getReceiver().getMemberId(),
                messageRoom.getFirstMessage(),
                messageRoom.getPost().getPostId(),
                messageRoom.getCreatedDate()
        );
    }
}
