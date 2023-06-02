package efub.assignment.community.messageroom.controller;

import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.messageroom.domain.MessageRoom;
import efub.assignment.community.messageroom.dto.MessageRoomExistsRequestDto;
import efub.assignment.community.messageroom.dto.MessageRoomRequestDto;
import efub.assignment.community.messageroom.dto.MessageRoomResponseDto;
import efub.assignment.community.messageroom.service.MessageRoomService;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/messagerooms")
@RequiredArgsConstructor
public class MessageRoomController {
    public final MessageRoomService messageRoomService;
    public final MemberService memberService;

    // 쪽지방 생성
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageRoomResponseDto messageRoomCreate(@RequestBody MessageRoomRequestDto requestDto) {
        MessageRoom messageRoom = messageRoomService.createMessageRoom(requestDto);
        return MessageRoomResponseDto.from(messageRoom);
    }

    // 쪽지방 여부 조회
    @GetMapping("/exists")
    @ResponseStatus(value = HttpStatus.OK)
    public Long messageRoomExists(@RequestBody MessageRoomExistsRequestDto requestDto) {
        MessageRoom messageRoom = messageRoomService.existsMessageRoom(requestDto);
        Long messageRoomId = messageRoom.getMessageRoomId();
        return messageRoomId;
    }

    // 쪽지방 목록 조회
    @GetMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<MessageRoomResponseDto> messageRoomListFind(@PathVariable Long memberId) {
        List<MessageRoom> messageRoomList = messageRoomService.findMessageRoomList(memberId);
        return messageRoomList.stream().map(MessageRoomResponseDto::from).collect(Collectors.toList());
    }

    // 쪽지방 삭제
    @DeleteMapping("/{messageRoomId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String messageRoomRemove(@PathVariable Long messageRoomId) {
        messageRoomService.removeMessageRoom(messageRoomId);
        return "성공적으로 삭제되었습니다.";
    }
}
