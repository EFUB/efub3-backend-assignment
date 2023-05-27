package efub.backend.assignment.messageroom.service;

import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.member.repository.MemberRepository;
import efub.backend.assignment.messageroom.domain.MessageRoom;
import efub.backend.assignment.messageroom.dto.MessageRoomRequestDto;
import efub.backend.assignment.messageroom.dto.MessageRoomResponseDto;
import efub.backend.assignment.messageroom.repository.MessageRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageRoomService {
    private final MessageRoomRepository messageRoomRepository;
    private final MemberRepository memberRepository;

    // 쪽지방 생성
    public MessageRoom createMessageRoom(MessageRoomRequestDto requestDto) {

        Member senderId = memberRepository.findById(requestDto.getSenderId().getMemberId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 계정입니다."));
        Member receiverId = memberRepository.findById(requestDto.getReceiverId().getMemberId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 계정입니다."));

        return messageRoomRepository.save(
            MessageRoom.builder()
                .senderId(requestDto.getSenderId())
                .receiverId(requestDto.getReceiverId())
                .messageContent(requestDto.getMessageContent())
                .messageId(requestDto.getMessageId())
                .build()
        );
    }

    // 쪽지방 조회
    public MessageRoom getMessageRoom(Long roomId) {
        return messageRoomrepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쪽지방 입니다."));
    }

    /*
    public MessageRoomResponseDto getMessageRoom(Long roomId) {
        MessageRoom messageRoom = messageRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쪽지방 입니다."));
        return new MessageRoomResponseDto(messageRoom);
    }

    public List<MessageRoomResponseDto> getAllMessageRooms() {
        List<MessageRoom> messageRooms = messageRoomRepository.findAll();
        return MessageRoomResponseDto.toList(messageRooms);
    }

     */

    // 쪽지방 존재 여부 확인
     public boolean doesMessageRoomExist(Long senderId, Long receiverId, Long messageId) {
        return messageRoomRepository.existsBySenderIdAndReceiverIdAndMessageId(senderId, receiverId, messageId);
    }

    // 쪽지방 삭제
    public void deleteMessageRoom(Long roomId) {
        MessageRoom messageRoom = messageRoomRepository.findById(roomId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 쪽지방입니다."));
        messageRoomRepository.deleteById(roomId);
    }
}