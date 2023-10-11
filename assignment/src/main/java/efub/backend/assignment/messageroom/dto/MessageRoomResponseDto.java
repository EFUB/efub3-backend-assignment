package efub.backend.assignment.messageroom.dto;

/*
{
    "roomId" : 1,
    "senderId" : 1,
    "receiverId" : 2,
    "messsgeContent" : "첫번째 쪽지",
    "createdDated" : "2023-05-27T12:50:12.666667"
}
 */

import efub.backend.assignment.messageroom.domain.MessageRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MessageRoomResponseDto {
    private Long roomId;
    private Long sender;
    private Long receiver;
    private Long postId;
    private LocalDateTime createdDate;

    public MessageRoomResponseDto(Long roomId, Long sender, Long receiver, Long postId, LocalDateTime createdDate) {
        this.roomId = roomId;
        this.sender = sender;
        this.receiver = receiver;
        this.postId = postId;
        this.createdDate = createdDate;
    }

    public static MessageRoomResponseDto from(MessageRoom messageRoom) {
        return new MessageRoomResponseDto(
                messageRoom.getRoomId(),
                messageRoom.getSender().getMemberId(),
                messageRoom.getReceiver().getMemberId(),
                messageRoom.getPost().getPostId(),
                messageRoom.getCreatedDate()
        );
    }
}
