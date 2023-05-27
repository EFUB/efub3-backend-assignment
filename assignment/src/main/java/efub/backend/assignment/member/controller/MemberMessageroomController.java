package efub.backend.assignment.member.controller;

import efub.backend.assignment.messageroom.dto.MessageRoomResponseDto;
import efub.backend.assignment.messageroom.service.MessageRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members/{memberId}/messagerooms")
@RequiredArgsConstructor
public class MemberMessageroomController {
    private final MessageRoomService messageroomService;

    @GetMapping
    public List<MessageRoomResponseDto> getMemberMessageRooms(@PathVariable Long memberId) {
        return messageroomService.getRoomList(memberId);
    }

    @DeleteMapping("/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessageRoom(@PathVariable Long memberId, @PathVariable Long roomId) {
        messageroomService.deleteMessageRoom(roomId);
    }
}