package efub.backend.assignment.message.controller;

import efub.backend.assignment.message.domain.Message;
import efub.backend.assignment.message.dto.MessageRequestDto;
import efub.backend.assignment.message.dto.MessageResponseDto;
import efub.backend.assignment.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController // @Controller와 @ResponseBody의 기능을 동시에 사용 → 해당 컨트롤러가 리턴하는 객체는 JSON 형태로 전달됨
@RequestMapping("/messages") // 경로 맵핑
@RequiredArgsConstructor // lombok
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponseDto messageAdd(@RequestBody MessageRequestDto requestDto){
        Message message = messageService.addMessage(requestDto);
        return MessageResponseDto.from(message);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<MessageResponseDto> messageListFind() {
        List<Message> messageList = messageService.findMessageList();
        return messageList.stream().map(MessageResponseDto::from).collect(Collectors.toList());
    }

    @GetMapping("/{messageId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MessageResponseDto messageFind(@PathVariable Long messageId) {
        Message message = messageService.findMessage(messageId);
        return MessageResponseDto.from(message);
    }
}