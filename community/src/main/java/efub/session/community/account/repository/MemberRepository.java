package efub.session.community.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository <Member, Long{
    Boolean existsByEmail(String email);
}
