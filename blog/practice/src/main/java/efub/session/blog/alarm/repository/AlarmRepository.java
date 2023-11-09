package efub.session.blog.alarm.repository;

import efub.session.blog.alarm.domain.Alarm;
import efub.session.blog.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findAllByMember(Member member);
}
