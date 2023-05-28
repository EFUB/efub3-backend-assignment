package efub.session.blog.message.service;

import efub.session.blog.member.domain.Member;
import efub.session.blog.member.service.MemberService;
import efub.session.blog.message.domain.Message;
import efub.session.blog.message.dto.MessageRequestDto;
import efub.session.blog.message.repository.MessageRepository;
import efub.session.blog.messageRoom.domain.MessageRoom;
import efub.session.blog.messageRoom.repository.MessageRoomRepository;
import efub.session.blog.messageRoom.service.MessageRoomService;
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
    private final MessageRoomService messageRoomService;
    private final MessageRoomRepository messageRoomRepository;
    private final MemberService memberService;

    //쪽지 생성
    public Message createMessage(MessageRequestDto requestDto) {
        MessageRoom messageRoom = messageRoomRepository.findById(requestDto.getMessageRoomId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쪽지방입니다."));
        Member sender = memberService.findMemberById(requestDto.getSenderId());
        Member receiver = memberService.findMemberById(requestDto.getReceiverId());

        return messageRepository.save(
                Message.builder()
                        .messageRoomId(messageRoom)
                        .sender(sender)
                        .receiver(receiver)
                        .content(requestDto.getContent())
                        .build()
        );
    }

    //쪽지 목록 조회
    @Transactional(readOnly = true)
    public List<Message> findMessageList(Long senderId, Long receiverId){
        Member sender = memberService.findMemberById(senderId);
        Member receiver = memberService.findMemberById(receiverId);
        return messageRepository.findAllBySenderAndReceiver(sender, receiver);
    }
}
