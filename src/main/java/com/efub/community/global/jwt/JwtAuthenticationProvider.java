package com.efub.community.global.jwt;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationProvider {

	private static final Long ACCESS_TOKEN_VALID_TIME = Duration.ofMinutes(30).toMillis(); // 만료시간 30분
	private static final Long REFRESH_TOKEN_VALID_TIME = Duration.ofDays(14).toMillis(); // 만료시간 2주

	private final SecretKey secretKey;

	/**
	 * JWT 시크릿 키를 Base64로 인코딩한 값으로 초기화한다.
	 */
	public JwtAuthenticationProvider(@Value("${jwt.secret}") String secret) {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		this.secretKey = Keys.hmacShaKeyFor(keyBytes);
	}

	/**
	 * JWT 토큰을 생성한다.
	 * @param authentication : 인증이 완료된 인증 객체
	 * @return JwtToken : JWT
	 */
	public JwtToken generateJwt(Authentication authentication) {
		long now = new Date(System.currentTimeMillis()).getTime();
		// Access Token 생성
		String accessToken = createAccessToken(authentication.getAuthorities(), authentication.getName(), now);
		// Refresh Token 생성
		String refreshToken = Jwts.builder()
			.expiration(new Date(now + REFRESH_TOKEN_VALID_TIME))
			.signWith(secretKey)
			.compact();
		return new JwtToken(authentication.getName(), accessToken, refreshToken);
	}

	private String createAccessToken(Collection<? extends GrantedAuthority> authorities, String subject, long now) {
		// 권한 정보를 가져옴
		String auth = authorities.stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(","));
		return Jwts.builder()
			.subject(subject)
			.claim("auth", auth)
			.expiration(new Date(now + ACCESS_TOKEN_VALID_TIME))
			.signWith(secretKey)
			.compact();
	}

	/**
	 * 토큰에서 유저 정보를 꺼내 반환하는 메소드
	 * @param token
	 * @return 유저 인증 정보
	 */
	public Authentication authenticate(String token) {
		// 토큰 복호화
		Claims claims = parseClaims(token);
		Collection<? extends GrantedAuthority> authorities = getAuthorities(claims);

		// 유저 인증 정보 생성
		UserDetails principal = new User(claims.getSubject(), "", authorities);
		log.debug("User principal: {}, {}", principal.getUsername(), principal.getPassword());
		return new UsernamePasswordAuthenticationToken(principal, "", authorities);
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Claims claims) {
		if (claims.get("auth") == null) {
			throw new SecurityException("Unauthorized JWT Token");
		}
		// 권한 정보를 꺼냄
		return Arrays.stream(claims.get("auth").toString().split(","))
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());
	}

	/**
	 * 리프레시 토큰을 기반으로 새로운 액세스 토큰을 발급합니다.
	 * @param jwtToken 기존 JWT
	 * @return 새로 발급한 액세스 토큰
	 */
	public String refresh(JwtToken jwtToken) {
		validateToken(jwtToken.getRefreshToken());

		Claims claims = parseClaims(jwtToken.getAccessToken());
		Collection<? extends GrantedAuthority> authorities = getAuthorities(claims);

		long now = new Date(System.currentTimeMillis()).getTime();
		return createAccessToken(authorities, claims.getSubject(), now);
	}

	/**
	 * 토큰을 검증합니다.
	 * @param token 검증할 토큰
	 * @return 유효성
	 */
	public boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
			log.debug("토큰 검증 성공");
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("Invalid JWT Token", e);
		} catch (ExpiredJwtException e) {
			log.info("Expired JWT Token", e);
		} catch (UnsupportedJwtException e) {
			log.info("Unsupported JWT Token", e);
		} catch (IllegalArgumentException e) {
			log.info("JWT claims string is empty.", e);
		}
		return false;
	}

	/**
	 * 토큰을 복호화한 후 페이로드를 반환합니다.
	 * @param token 복호화할 토큰
	 * @return 토큰의 페이로드
	 */
	private Claims parseClaims(String token) {
		try {
			return Jwts.parser().verifyWith(secretKey).build()
				.parseSignedClaims(token).getPayload();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
}
