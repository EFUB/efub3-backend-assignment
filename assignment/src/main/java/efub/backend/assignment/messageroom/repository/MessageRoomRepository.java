package efub.backend.assignment.messageroom.repository;

import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.messageroom.domain.MessageRoom;
import efub.backend.assignment.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRoomRepository extends JpaRepository<MessageRoom, Long> {
    Optional<MessageRoom> findByMessageRoomIdAndMessageRoomSender_MemberId(Long messageRoomId, Member memberId);

    // 쪽지방 검색
    Optional<MessageRoom> findByFirstSenderAndFirstReceiverAndCreatedFrom(Member firstSender, Member firstReceiver, Post createdFrom);

    // 특정 유저의 전체 쪽지방 목록 조회
    List<MessageRoom> findAllByFirstSenderOrFirstReceiver(Member firstSender, Member firstReceiver);

    boolean existsBySenderIdAndReceiverIdAndMessageId(Long senderId, Long receiverId, Long messageId);
}
