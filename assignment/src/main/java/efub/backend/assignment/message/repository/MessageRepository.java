package efub.backend.assignment.message.repository;

import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // 특정 쪽지방의 쪽지 목록 조회
    List<Message> findAllByMessageRoom_RoomId(Long messageRoomId);
    List<Message> findAllBySenderAndReceiver(Member sender, Member receiver);
}
