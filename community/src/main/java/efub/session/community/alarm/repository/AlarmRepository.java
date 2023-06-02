package efub.session.community.alarm.repository;

import efub.session.community.alarm.domain.Alarm;
import efub.session.community.account.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm,Long> {
    List<Alarm> findAllByMember(Member member);
}