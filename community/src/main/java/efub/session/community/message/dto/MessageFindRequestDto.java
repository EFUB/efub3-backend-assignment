package efub.session.community.message.dto;

import efub.session.community.message.domain.Message;
import efub.session.community.messageroom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageFindRequestDto {
    private Boolean isReceived;
    private String  messageContent;
    private LocalDateTime messageCreated;

    @Builder
    public MessageFindRequestDto(Boolean isReceived, String messageContent, LocalDateTime messageCreated){
        this.isReceived=isReceived;
        this.messageContent=messageContent;
        this.messageCreated=messageCreated;
    }
}
