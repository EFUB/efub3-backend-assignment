package efub.session.community.message.repository;

import efub.session.community.message.domain.Message;
import efub.session.community.messageroom.domain.MessageRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByMessageRoom(MessageRoom messageRoom);
}
