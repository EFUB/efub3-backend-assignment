package efub.backend.assignment.messageroom.controller;

import efub.backend.assignment.messageroom.domain.MessageRoom;
import efub.backend.assignment.messageroom.dto.MessageRoomCheckRequestDto;
import efub.backend.assignment.messageroom.dto.MessageRoomListResponseDto;
import efub.backend.assignment.messageroom.dto.MessageRoomRequestDto;
import efub.backend.assignment.messageroom.dto.MessageRoomResponseDto;
import efub.backend.assignment.messageroom.service.MessageRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messagerooms")
@RequiredArgsConstructor
public class MessageRoomController {
    private final MessageRoomService messageRoomService;

    // 쪽지방 생성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageRoomResponseDto createMessageRoom(@RequestBody @Valid final MessageRoomRequestDto requestDto)
    {
        MessageRoom messageRoom = messageRoomService.createMessageRoom(requestDto);
        return MessageRoomResponseDto.from(messageRoom);
    }

    // 쪽지방 여부 조회
    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public Boolean messageRoomCheck(@RequestBody @Valid final MessageRoomCheckRequestDto requestDto)
    {
        Boolean checkedRoomId = messageRoomService.checkMessageRoom(requestDto);
        return checkedRoomId;
    }

    // 쪽지방 목록 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MessageRoomListResponseDto getMessageRoomList(@RequestParam Long memberId) {
        List<MessageRoom> messageRoomList = messageRoomService.getMessageRoomList(memberId);
        return MessageRoomListResponseDto.of(messageRoomList);
    }

    // 쪽지방 삭제
    @DeleteMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteMessageRoom(@PathVariable Long roomId) {
        messageRoomService.deleteMessageRoom(roomId);
        return roomId + " 쪽지방이 성공적으로 삭제되었습니다.";
    }
}