package efub.backend.assignment.message.service;

import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.member.repository.MemberRepository;
import efub.backend.assignment.member.service.MemberService;
import efub.backend.assignment.message.domain.Message;
import efub.backend.assignment.message.dto.MessageRequestDto;
import efub.backend.assignment.message.repository.MessageRepository;
import efub.backend.assignment.messageroom.domain.MessageRoom;
import efub.backend.assignment.messageroom.repository.MessageRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;
    private final MessageRoomRepository messageRoomRepository;
    private final MemberService memberService;

    public Message addMessage(MessageRequestDto requestDto) {
        MessageRoom messageRoom = messageRoomRepository.findById(requestDto.getMessageRoomId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쪽지방입니다."));

        Member sender = memberRepository.findById(requestDto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

        Member receiver = memberRepository.findById(requestDto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

        return messageRepository.save(
                Message.builder()
                        .sender(sender)
                        .receiver(receiver)
                        .content(requestDto.getContent())
                        .messageRoom(messageRoom)
                        .build()
        );
    }

    // 쪽지 목록 조회
    @Transactional(readOnly = true)
    public List<Message> findMessageList(Long messageRoomId) {
        return messageRepository.findAllByMessageRoom_RoomId(messageRoomId);
    }
}
