package efub.session.blog.message.controller;

import efub.session.blog.message.domain.Message;
import efub.session.blog.message.dto.MessageRequestDto;
import efub.session.blog.message.dto.MessageResponseDto;
import efub.session.blog.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    public final MessageService messageService;

    //쪽지 생성
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponseDto messageCreate(@RequestBody MessageRequestDto requestDto){
        Message message = messageService.createMessage(requestDto);
        return MessageResponseDto.from(message);
    }

    @GetMapping("/{messageRoomId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<MessageResponseDto> messageListFind(@PathVariable Long sender, @PathVariable Long receiver){
        List<Message> messageList = messageService.findMessageList(sender,receiver);
        return messageList.stream().map(MessageResponseDto::from).collect(Collectors.toList());
    }
}
