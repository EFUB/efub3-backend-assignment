package efub.session.blog.messageRoom.service;

import efub.session.blog.member.domain.Member;
import efub.session.blog.member.service.MemberService;
import efub.session.blog.messageRoom.domain.MessageRoom;
import efub.session.blog.messageRoom.dto.MessageRoomExistRequestDto;
import efub.session.blog.messageRoom.dto.MessageRoomRequestDto;
import efub.session.blog.messageRoom.repository.MessageRoomRepository;
import efub.session.blog.post.domain.Post;
import efub.session.blog.post.service.PostService;
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
    
    //쪽지방 생성
    public MessageRoom addMessageRoom(MessageRoomRequestDto requestDto) {
        Member sender = memberService.findMemberById(requestDto.getSenderId());
        Member receiver = memberService.findMemberById(requestDto.getReceiverId());
        Post post = postService.findPost(requestDto.getPostId());

        return messageRoomRepository.save(
                MessageRoom.builder()
                        .sender(sender)
                        .receiver(receiver)
                        .content(requestDto.getFirstMessage())
                        .postId(post)
                        .build()
        );
    }
    
    //쪽지방 여부 조회
    @Transactional(readOnly = true)
    public MessageRoom existMessageRoom(MessageRoomExistRequestDto requestDto) {
        Member sender = memberService.findMemberById(requestDto.getSenderId());
        Member receiver = memberService.findMemberById(requestDto.getReceiverId());
        Post post = postService.findPost(requestDto.getPostId());

        return messageRoomRepository.findByPostIdAndSenderIdAndReceiverId(post.getPostId(), sender.getMemberId(), receiver.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쪽지방입니다."));
    }
    
    //쪽지방 목록 조회
    @Transactional(readOnly = true)
    public List<MessageRoom> findMessageRoomList(Long memberId) {
        Member member = memberService.findMemberById(memberId);
        return messageRoomRepository.findAllByMember(member);
    }
    
    //쪽지방 삭제
    public void removeMessageRoom(Long messageRoomId) {
        MessageRoom messageRoom = messageRoomRepository.findById(messageRoomId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        messageRoomRepository.delete(messageRoom);
    }
}
