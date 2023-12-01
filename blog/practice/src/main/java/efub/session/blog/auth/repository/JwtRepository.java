package efub.session.blog.auth.repository;

import java.util.Optional;

import efub.session.blog.auth.entity.JwtToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JwtRepository extends CrudRepository<JwtToken, String> {

    Optional<JwtToken> findByAccessToken(String accessToken);
}
