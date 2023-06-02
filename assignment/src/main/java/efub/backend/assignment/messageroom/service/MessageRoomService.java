package efub.backend.assignment.messageroom.service;

import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.member.repository.MemberRepository;
import efub.backend.assignment.member.service.MemberService;
import efub.backend.assignment.messageroom.domain.MessageRoom;
import efub.backend.assignment.messageroom.dto.MessageRoomCheckRequestDto;
import efub.backend.assignment.messageroom.dto.MessageRoomRequestDto;
import efub.backend.assignment.messageroom.repository.MessageRoomRepository;
import efub.backend.assignment.notification.service.NotificationService;
import efub.backend.assignment.post.domain.Post;
import efub.backend.assignment.post.repository.PostRepository;
import efub.backend.assignment.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MessageRoomService {
    private final MessageRoomRepository messageRoomRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PostRepository postRepository;
    private final PostService postService;

    // 쪽지방 생성
    public MessageRoom createMessageRoom(MessageRoomRequestDto requestDto) {

        Member sender = memberRepository.findById(requestDto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Member receiver = memberRepository.findById(requestDto.getReceiverId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Post postId = postRepository.findById(requestDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 포스트 입니다."));

        return messageRoomRepository.save(
                MessageRoom.builder()
                        .sender(sender)
                        .receiver(receiver)
                        .firstMessage(requestDto.getFirstMessage())
                        .post(postId)
                        .build()
        );
    }

    // 쪽지방 목록 조회
    public List<MessageRoom> getMessageRoomList(Long memberId) {
        Member member = memberService.findMemberById(memberId);
        return messageRoomRepository.findAllByFirstSenderOrFirstReceiver(member, member);
    }

    // 쪽지방 검색
    public Long findMessageRoomId(Long senderId, Long receiverId, Long postId) {
        Member firstSender = memberService.findMemberById(senderId);
        Member firstReceiver = memberService.findMemberById(receiverId);
        Post createdFrom = postService.findPost(postId);

        MessageRoom messageRoom = messageRoomRepository.findByFirstSenderAndFirstReceiverAndCreatedFrom(firstSender, firstReceiver, createdFrom)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쪽지방입니다."));
        return messageRoom.getRoomId();
    }

    // 쪽지방 존재 여부 확인
    public boolean checkMessageRoom(MessageRoomCheckRequestDto requestDto) {
        Member sender = memberRepository.findById(requestDto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Member receiver = memberRepository.findById(requestDto.getReceiverId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Post post = postRepository.findById(requestDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 포스트 입니다."));

        return messageRoomRepository.existsBySenderIdAndReceiverIdAndMessageId(
                sender.getMemberId(), receiver.getMemberId(), post.getPostId()
        );
    }

    // 쪽지방 삭제
    public void deleteMessageRoom(Long roomId) {
        MessageRoom messageRoom = messageRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쪽지방입니다."));
        messageRoomRepository.deleteById(roomId);
    }
}