package efub.assignment.community.messageroom.repository;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.messageroom.domain.MessageRoom;
import efub.assignment.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRoomRepository extends JpaRepository<MessageRoom, Long> {
    MessageRoom existsBySenderAndReceiverAndPost(Long senderId, Long receiverId, Long postId);

    List<MessageRoom> findAllBySender(Member sender);
}
