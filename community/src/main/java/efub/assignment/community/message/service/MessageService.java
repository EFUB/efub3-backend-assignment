package efub.assignment.community.message.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.message.domain.Message;
import efub.assignment.community.message.dto.MessageRequestDto;
import efub.assignment.community.message.repository.MessageRepository;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.service.MessageRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberService memberService;

    public Message addMessage(MessageRoom messageRoom, Long senderId, String content){
        Member sender = memberService.findMemberById(senderId);

        return messageRepository.save(
                Message.builder()
                        .messageRoom(messageRoom)
                        .sender(sender)
                        .content(content)
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public Boolean isReceived(Message message, Long memberId){
        Member member = memberService.findMemberById(memberId);
        if(message.getSender().equals(member))
            return false;
        else
            return true;
    }
}
