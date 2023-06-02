package efub.assignment.community.message.dto;

import efub.assignment.community.message.domain.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MessageResponseDto {
    private String content;
    private LocalDateTime createdDate;
    private Boolean isReceived;

    public MessageResponseDto(Message message, Boolean isReceived){
        this.content = message.getContent();
        this.createdDate = message.getCreatedDate();
        this.isReceived = isReceived;
    }
}
