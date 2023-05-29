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

    // 쪽지 생성
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponseDto messageAdd(@RequestBody MessageRequestDto requestDto){
        Message message = messageService.addMessage(requestDto);
        return MessageResponseDto.from(message);
    }

    // 쪽지 목록 조회
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<MessageResponseDto> findMessageList(@PathVariable Long messageRoomId) {
        List<Message> messageList = messageService.findMessageList(messageRoomId);
        return messageList.stream().map(MessageResponseDto::from).collect(Collectors.toList());
    }
}