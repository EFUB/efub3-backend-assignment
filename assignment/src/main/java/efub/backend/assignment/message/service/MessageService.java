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
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다.")); // Board를 넘겨받는 dto가 request 파라미터

        return messageRepository.save( // mysql에서는 insert
                Message.builder()
                        .senderId(requestDto.getSenderId())
                        .receiverId(requestDto.getReceiverId())
                        .content(requestDto.getContent())
                        .messageRoom(messageRoom)
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public List<Message> findMessageList() {
        return messageRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Message findMessage(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

}
