package efub.backend.assignment.member.repository;

import efub.backend.assignment.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsByEmail(String email);

    // 이메일 형식 확인 메서드 추가
    boolean existsByEmailLike(String pattern);
}
