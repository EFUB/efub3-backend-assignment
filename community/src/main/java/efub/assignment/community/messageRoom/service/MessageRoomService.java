package efub.assignment.community.messageRoom.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.message.domain.Message;
import efub.assignment.community.message.service.MessageService;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.dto.MessageRoomCreateRequestDto;
import efub.assignment.community.messageRoom.dto.MessageRoomSearchRequestDto;
import efub.assignment.community.messageRoom.repository.MessageRoomRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageRoomService {
    private final MessageRoomRepository messageRoomRepository;
    private final MemberService memberService;
    private final PostService postService;
    private final MessageService messageService;

    public MessageRoom addRoom(MessageRoomCreateRequestDto requestDto) {
        Member creater = memberService.findMemberById(requestDto.getCreaterId());
        Member receiver = memberService.findMemberById(requestDto.getReceiverId());
        Post post = postService.findPost(requestDto.getPostId());

        MessageRoom messageRoom = messageRoomRepository.save(
                MessageRoom.builder()
                        .creater(creater)
                        .receiver(receiver)
                        .post(post)
                        .content(requestDto.getContent())
                        .build()
        );

        Message message = messageService.addMessage(
                messageRoom, creater.getMemberId(), requestDto.getContent());

        return messageRoom;
    }

    @Transactional(readOnly = true)
    public MessageRoom existsRoom(MessageRoomSearchRequestDto requestDto) {
        Member creater = memberService.findMemberById(requestDto.getCreaterId());
        Member receiver = memberService.findMemberById(requestDto.getReceiverId());
        Post post = postService.findPost(requestDto.getPostId());

        MessageRoom room = messageRoomRepository.findByCreaterAndReceiverAndPost(creater, receiver, post);
        if (room == null)
            room = messageRoomRepository.findByCreaterAndReceiverAndPost(receiver, creater, post);

        return room;
    }

    @Transactional(readOnly = true)
    public List<MessageRoom> findRoomList(Long memberId) {
        Member finder = memberService.findMemberById(memberId);
        return messageRoomRepository.findAllByCreaterOrReceiver(finder, finder);
    }

    @Transactional(readOnly = true)
    public MessageRoom findRoomById(Long messageRoomId) {
        return messageRoomRepository.findById(messageRoomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쪽지방입니다."));
    }

    @Transactional(readOnly = true)
    public Member findTalkWith(MessageRoom messageRoom, Long memberId) {
        Member member = memberService.findMemberById(memberId);

        if (member.equals(messageRoom.getCreater()))
            return messageRoom.getReceiver();
        else if (member.equals(messageRoom.getReceiver()))
            return messageRoom.getCreater();
        else
            return null;
    }

    public void removeRoom(Long messageRoomId) {
        MessageRoom messageRoom = findRoomById(messageRoomId);
        messageRoomRepository.delete(messageRoom);
    }
}
