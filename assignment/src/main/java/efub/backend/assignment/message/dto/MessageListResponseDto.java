package efub.backend.assignment.message.dto;

import efub.backend.assignment.message.domain.Message;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MessageListResponseDto {

    private List<MessageResponseDto> messages;
    private Integer count;

    public static efub.backend.assignment.message.dto.MessageListResponseDto of(List<Message> messageList) {
        return efub.backend.assignment.message.dto.MessageListResponseDto.builder()
                .messages(messageList.stream().map(MessageResponseDto::from).collect(Collectors.toList()))
                .count(messageList.size())
                .build();
    }
}