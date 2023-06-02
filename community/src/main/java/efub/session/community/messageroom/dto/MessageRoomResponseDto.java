
package efub.session.community.messageroom.dto;
import efub.session.community.messageroom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoomResponseDto {
    private Long messageRoomId;
    private Long senderId; // 처음 보낸 사람 id
    private Long receiverId; // 처음 받는 사람 id
    private String firstMessage;
    private LocalDateTime created;


    public MessageRoomResponseDto(MessageRoom messageRoom){
        this.messageRoomId =messageRoom.getMessageRoomId();
        this.senderId=messageRoom.getStarter().getMemberId();
        this.receiverId=messageRoom.getReceiver().getMemberId();
        this.firstMessage= messageRoom.getFirstMessage();
        this.created=messageRoom.getCreatedDate();

    }

}