package efub.session.blog.message.repository;

import efub.session.blog.member.domain.Member;
import efub.session.blog.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllBySenderAndReceiver(Member sender, Member receiver);
}
