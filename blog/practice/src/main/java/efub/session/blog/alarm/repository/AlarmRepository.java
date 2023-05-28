package efub.session.blog.alarm.repository;

import efub.session.blog.alarm.domain.Alarm;
import efub.session.blog.member.domain.Member;

import java.util.List;

public interface AlarmRepository {
    List<Alarm> findAllByMember(Member member);
}
