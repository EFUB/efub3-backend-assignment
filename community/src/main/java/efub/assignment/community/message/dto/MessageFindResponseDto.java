package efub.assignment.community.message.dto;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class MessageFindResponseDto {
    private Long messageRoomId;
    private Long talkWithId;
    private List<MessageResponseDto> message = new ArrayList<>();

    public MessageFindResponseDto(MessageRoom messageRoom, Member talkWith, List<MessageResponseDto> messageResponseDtoList){
        this.messageRoomId = messageRoom.getMessageRoomId();
        this.talkWithId = talkWith.getMemberId();
        this.message = messageResponseDtoList;
    }
}
