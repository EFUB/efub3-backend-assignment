package efub.session.blog.messageRoom.repository;

import efub.session.blog.member.domain.Member;
import efub.session.blog.messageRoom.domain.MessageRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRoomRepository extends JpaRepository<MessageRoom,Long> {
    Optional<MessageRoom> findByPostIdAndSenderAndReceiver(Long postId, Long senderId, Long receiverId);

    List<MessageRoom> findAllBySenderOrReceiver(Member sender, Member receiver);
}
