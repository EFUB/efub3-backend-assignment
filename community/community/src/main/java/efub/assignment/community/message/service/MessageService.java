package efub.assignment.community.message.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.message.domain.Message;
import efub.assignment.community.message.dto.MessageRequestDto;
import efub.assignment.community.message.repository.MessageRepository;
import efub.assignment.community.messageroom.domain.MessageRoom;
import efub.assignment.community.messageroom.repository.MessageRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageRoomRepository messageRoomRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public Message createMessage(MessageRequestDto requestDto) {
        MessageRoom messageRoom = messageRoomRepository.findById(requestDto.getMessageRoomId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쪽지방입니다."));

        Member sender = memberRepository.findById(requestDto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

        Member receiver = memberRepository.findById(requestDto.getReceiverId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

        return messageRepository.save(
                Message.builder()
                        .messageRoomId(messageRoom)
                        .sender(sender)
                        .receiver(receiver)
                        .message(requestDto.getMessage())
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public List<Message> findMessageList(Long senderId, Long receiverId) {
        Member sender = memberService.findMemberById(senderId);
        Member receiver = memberService.findMemberById(receiverId);
        return messageRepository.findAllBySenderAndReceiver(sender, receiver);
    }
}
