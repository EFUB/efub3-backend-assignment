package efub.session.community.account.repository;

import efub.session.community.account.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository <Member, Long>{
    Boolean existsByEmail(String email);
    Optional<Member> findByEmail(String email); // 이메일로 계정을 조회
}
