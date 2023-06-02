package efub.assignment.community.messageroom.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.messageroom.domain.MessageRoom;
import efub.assignment.community.messageroom.dto.MessageRoomExistsRequestDto;
import efub.assignment.community.messageroom.dto.MessageRoomRequestDto;
import efub.assignment.community.messageroom.repository.MessageRoomRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageRoomService {
    private final MessageRoomRepository messageRoomRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final MemberService memberService;

    public MessageRoom createMessageRoom(MessageRoomRequestDto requestDto) {
        Member sender = memberRepository.findById(requestDto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

        Member receiver = memberRepository.findById(requestDto.getReceiverId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

        Post postId = postRepository.findById(requestDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        return messageRoomRepository.save(
                MessageRoom.builder()
                        .sender(sender)
                        .receiver(receiver)
                        .firstMessage(requestDto.getFirstMessage())
                        .post(postId)
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public MessageRoom existsMessageRoom(MessageRoomExistsRequestDto requestDto) {
        Member sender = memberRepository.findById(requestDto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

        Member receiver = memberRepository.findById(requestDto.getReceiverId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

        Post post = postRepository.findById(requestDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        return messageRoomRepository.existsBySenderAndReceiverAndPost(
                                     sender.getMemberId(), receiver.getMemberId(), post.getPostId());
    }

    @Transactional(readOnly = true)
    public List<MessageRoom> findMessageRoomList(Long memberId) {
        Member member = memberService.findMemberById(memberId);
        return messageRoomRepository.findAllBySender(member);
    }

    public void removeMessageRoom(Long messageRoomId) {
        MessageRoom messageRoom = messageRoomRepository.findById(messageRoomId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        messageRoomRepository.delete(messageRoom);
    }
}
