package efub.session.community.message.controller;

import efub.session.community.message.domain.Message;
import efub.session.community.message.dto.MessageRequestDto;
import efub.session.community.message.dto.MessageResponseDto;
import efub.session.community.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;
}
