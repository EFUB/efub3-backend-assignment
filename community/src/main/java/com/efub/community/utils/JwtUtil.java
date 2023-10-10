package com.efub.community.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }

    // 토큰을 만든다(access token, refresh token에서 호출됨)
    public static String createToken (String username, String key, long expireTimeMs) {
        Claims claims = Jwts.claims();  // 토큰에 담을 정보
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))  // 발행 시각
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs)) // 만료 시각
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    // access token을 만든다.
    public static String createAccessToken(String userName, String key, long expireTimeMs) {
        return createToken(userName, key, expireTimeMs);
    }

    // refresh token을 만든다.
    public static String createRefreshToken (String userName, String key, long expireTimeMs) {
        return createToken(userName, key, expireTimeMs);
    }

    public static Claims parseRefreshToken(String value, String key) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(value)
                .getBody();
    }
}
