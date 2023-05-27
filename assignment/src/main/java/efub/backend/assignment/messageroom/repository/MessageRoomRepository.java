package efub.backend.assignment.messageroom.repository;

import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.messageroom.domain.MessageRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRoomRepository extends JpaRepository<MessageRoom, Long> {
    Optional<MessageRoom> findByMessageRoomIdAndMessageRoomSender_MemberId(Long messageRoomId, Member memberId);

    boolean existsBySenderIdAndReceiverIdAndMessageId(Long senderId, Long receiverId, Long messageId);
}
