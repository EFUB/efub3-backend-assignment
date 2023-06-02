package efub.session.blog.messageRoom.controller;

import efub.session.blog.messageRoom.domain.MessageRoom;
import efub.session.blog.messageRoom.dto.MessageRoomExistRequestDto;
import efub.session.blog.messageRoom.dto.MessageRoomRequestDto;
import efub.session.blog.messageRoom.dto.MessageRoomResponseDto;
import efub.session.blog.messageRoom.service.MessageRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/messageRooms")
@RequiredArgsConstructor
public class MessageRoomController {
    private final MessageRoomService messageRoomService;

    //쪽지방 생성
    @PostMapping
    @ResponseStatus(value= HttpStatus.CREATED)
    public MessageRoomResponseDto messageRoomAdd(@RequestBody @Valid final MessageRoomRequestDto requestDto){
        MessageRoom messageRoom = messageRoomService.addMessageRoom(requestDto);
        return MessageRoomResponseDto.from(messageRoom);
    }

    //쪽지방 여부 조회
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Long messageRoomExist(@RequestBody @Valid final MessageRoomExistRequestDto requestDto){
        MessageRoom messageRoom = messageRoomService.existMessageRoom(requestDto);
        Long messageRoomId = messageRoom.getMessageRoomId();
        return messageRoomId;
    }

    //쪽지방 목록 조회
    @GetMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<MessageRoomResponseDto> messageRoomListFind(@PathVariable Long memberId){
        List<MessageRoom> messageRoomList=messageRoomService.findMessageRoomList(memberId);
        return messageRoomList.stream().map(MessageRoomResponseDto::from).collect(Collectors.toList());
    }

    //쪽지방 삭제
    @DeleteMapping("/{messageRoomId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String messageRoomRemove(@PathVariable Long messageRoomId){
        messageRoomService.removeMessageRoom(messageRoomId);
        return "성공적으로 삭제되었습니다.";
    }

}