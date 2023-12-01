package com.efub.community.global.jwt;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface JwtTokenRepository extends CrudRepository<JwtToken, String> {

	Optional<JwtToken> findByAccessToken(String accessToken);
}
