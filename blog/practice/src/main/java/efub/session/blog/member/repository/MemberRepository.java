package efub.session.blog.member.repository;

import efub.session.blog.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Boolean existsByEmail(String email);
}
