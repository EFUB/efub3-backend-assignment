package efub.backend.assignment.messageroom.controller;

import efub.backend.assignment.messageroom.domain.MessageRoom;
import efub.backend.assignment.messageroom.dto.MessageRoomRequestDto;
import efub.backend.assignment.messageroom.dto.MessageRoomResponseDto;
import efub.backend.assignment.messageroom.service.MessageRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messagerooms")
@RequiredArgsConstructor
public class MessageRoomController {
    private final MessageRoomService messageRoomService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageRoomResponseDto createMessageRoom(@RequestBody MessageRoomRequestDto requestDto) {
        MessageRoom messageRoom = messageRoomService.createMessageRoom(requestDto);
        return new MessageRoomResponseDto(messageRoom);
    }

    @GetMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public MessageRoomResponseDto getMessageRoom(@PathVariable Long roomId) {
        MessageRoom messageRoom = messageRoomService.getMessageRoom(roomId);
        return new MessageRoomResponseDto(messageRoom);
    }

    @DeleteMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteMessageRoom(@PathVariable Long roomId) {
        messageRoomService.deleteMessageRoom(roomId);
        return roomId + " 쪽지방이 성공적으로 삭제되었습니다.";
    }
}