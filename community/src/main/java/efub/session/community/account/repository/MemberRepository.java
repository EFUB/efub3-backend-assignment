package efub.session.community.account.repository;

import efub.session.community.account.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository <Member, Long>{
    Boolean existsByEmail(String email);
}
