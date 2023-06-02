package efub.assignment.community.message.controller;

import efub.assignment.community.message.domain.Message;
import efub.assignment.community.message.dto.MessageRequestDto;
import efub.assignment.community.message.dto.MessageResponseDto;
import efub.assignment.community.message.service.MessageService;
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
    private final MessageService messageService;

    // 쪽지 생성
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponseDto messageCreate(@RequestBody MessageRequestDto requestDto) {
        Message message = messageService.createMessage(requestDto);
        return MessageResponseDto.from(message);
    }

    // 쪽지방 내 쪽지 목록 조회
    @GetMapping("/{sender}/{receiver}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<MessageResponseDto> messageListFind(@PathVariable Long sender, @PathVariable Long receiver) {
        List<Message> messageList = messageService.findMessageList(sender, receiver);
        return messageList.stream().map(MessageResponseDto::from).collect(Collectors.toList());
    }
}
