package efub.backend.assignment.message.repository;

import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<Message> findByMessageIdAndWriter_MemberId(Long messageId, Long memberId);
    //네이밍으로 디비
}
