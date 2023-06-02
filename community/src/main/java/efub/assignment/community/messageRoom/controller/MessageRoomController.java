package efub.assignment.community.messageRoom.controller;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.message.domain.Message;
import efub.assignment.community.message.dto.MessageFindResponseDto;
import efub.assignment.community.message.dto.MessageResponseDto;
import efub.assignment.community.message.service.MessageService;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.dto.MessageRoomCreateRequestDto;
import efub.assignment.community.messageRoom.dto.MessageRoomCreateResponseDto;
import efub.assignment.community.messageRoom.dto.MessageRoomResponseDto;
import efub.assignment.community.messageRoom.dto.MessageRoomSearchRequestDto;
import efub.assignment.community.messageRoom.service.MessageRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message_rooms")
public class MessageRoomController {

    private final MessageRoomService messageRoomService;
    private final MessageService messageService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageRoomCreateResponseDto roomAdd(@RequestBody @Valid MessageRoomCreateRequestDto requestDto){
        MessageRoom messageRoom = messageRoomService.addRoom(requestDto);
        return new MessageRoomCreateResponseDto(messageRoom);
    }

    // 방 여부 조회
    @GetMapping("/search")
    @ResponseStatus(value = HttpStatus.OK)
    public Long roomFind(@RequestBody @Valid MessageRoomSearchRequestDto requestDto){
        MessageRoom messageRoom = messageRoomService.existsRoom(requestDto);
        return messageRoom.getMessageRoomId();
    }

    // 방 목록 조회
    @GetMapping("/list")
    @ResponseStatus(value = HttpStatus.OK)
    public List<MessageRoomResponseDto> roomListFind(@RequestParam Long memberId){
        List<MessageRoom> messageRoomList = messageRoomService.findRoomList(memberId);
        List<MessageRoomResponseDto> responseDtoList = new ArrayList<>();

        for(MessageRoom room : messageRoomList){
            responseDtoList.add(
                    new MessageRoomResponseDto(room)
            );
        }

        return responseDtoList;
    }

    // 쪽지 목록 조회
    @GetMapping("/{messageRoomId}/messages")
    @ResponseStatus(value = HttpStatus.OK)
    public MessageFindResponseDto messageListFind(@PathVariable Long messageRoomId, @RequestParam Long memberId) {
        MessageRoom messageRoom = messageRoomService.findRoomById(messageRoomId);
        Member talkWith = messageRoomService.findTalkWith(messageRoom, memberId);

        List<MessageResponseDto> messageResponseDtoList = new ArrayList<>();

        for (Message message : messageRoom.getMessageList()) {
            messageResponseDtoList.add(
                    new MessageResponseDto(message, messageService.isReceived(message, memberId))
            );
        }

        return new MessageFindResponseDto(messageRoom, talkWith, messageResponseDtoList);
    }

    @DeleteMapping("/{messageRoomId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String roomRemove(@PathVariable Long messageRoomId){
        messageRoomService.removeRoom(messageRoomId);
        return "쪽지방이 삭제되었습니다.";
    }
}
