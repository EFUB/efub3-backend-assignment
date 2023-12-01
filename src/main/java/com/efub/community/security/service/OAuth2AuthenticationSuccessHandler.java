package com.efub.community.security.service;


import com.efub.community.global.CookieUtils;
import com.efub.community.global.jwt.JwtProvider;
import com.efub.community.global.redis.RedisService;
import com.efub.community.security.GoogleUserInfo;
import com.efub.community.security.repository.CookieAuthorizationRequestRepository;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtProvider jwtProvider;
    private final RedisService redisService;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final OAuth2AuthorizedClientService authorizedClientService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        log.info(attributes.toString());

        OAuth2AuthorizedClient auth2AuthorizedClient = authorizedClientService.loadAuthorizedClient(
                authToken.getAuthorizedClientRegistrationId(),
                authToken.getName());

        String oauthAccessToken = auth2AuthorizedClient.getAccessToken().getTokenValue();
        log.info("oauth access token:" + oauthAccessToken);

        OAuth2RefreshToken refreshToken = auth2AuthorizedClient.getRefreshToken();
        log.info("oauth refresh token: " + refreshToken);


        String email = null;

        GoogleUserInfo googleUserInfo = new GoogleUserInfo(attributes);
        email = googleUserInfo.getEmail();
        String oauthRefreshToken = refreshToken != null
                ? refreshToken.getTokenValue()
                : redisService.getData("GoogleRefreshToken:" + email);
        redisService.setData("GoogleAccessToken:" + email, oauthAccessToken);
        redisService.setData("GoogleRefreshToken:" + email, oauthRefreshToken);

        String targetUrl = determineTargetUrl(request, response, authentication);
        log.info("targetUrl = " + targetUrl);

        String url = makeRedirectUrl(email, targetUrl);

        ResponseCookie responseCookie = generateRefreshTokenCookie(email);
        response.setHeader("Set-Cookie", responseCookie.toString());
        response.getWriter().write(url);


        if (response.isCommitted()) {
            logger.info("응답이 이미 커밋된 상태입니다. " + url + "로 리다이렉트하도록 바꿀 수 없습니다.");
            return;
        }
        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, url);
    }


    private String makeRedirectUrl(String email, String redirectUrl) {

        redirectUrl = "http://localhost:3000";
        log.info(redirectUrl);

        String accessToken = jwtProvider.generateAccessToken(email);
        log.info(accessToken);

        return UriComponentsBuilder.fromHttpUrl(redirectUrl)
                .path("/google-login")
                .queryParam("accessToken", accessToken)
                .queryParam("redirectUrl", redirectUrl)
                .build()
                .encode()
                .toUriString();

    }

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUrl = CookieUtils.getCookie(request, CookieAuthorizationRequestRepository.REDIRECT_URL_PARAM_COOKIE_KEY).map(
                Cookie::getValue);
        String targetUrl = redirectUrl.orElse(getDefaultTargetUrl());
        return UriComponentsBuilder.fromUriString(targetUrl)
                .build().toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    public ResponseCookie generateRefreshTokenCookie(String email) {
        String refreshToken = jwtProvider.generateRefreshToken(email);
        Long refreshTokenValidationMs = jwtProvider.getRefreshTokenValidationMs();

        redisService.setData("RefreshToken:" + email, refreshToken, refreshTokenValidationMs);
        log.info("refresh:" + refreshToken);
        return ResponseCookie.from("refreshToken", refreshToken)
                .path("/") // 해당 경로 하위의 페이지에서만 쿠키 접근 허용. 모든 경로에서 접근 허용한다.
                .domain("localhost:8080") //TODO : 배포시 도메인 변경
                .maxAge(TimeUnit.MILLISECONDS.toSeconds(refreshTokenValidationMs)) // 쿠키 만료 시기(초). 없으면 브라우저 닫힐 때 제거
                .secure(true) // HTTPS 프로토콜 상에서 암호화된 요청을 위한 옵션
                .sameSite("None") // Same Site 요청을 물론 Cross Site으 요청에도 모두 전송 허용
                .httpOnly(true) // XSS 공격을 방어하기 위한 옵션
                .build();
    }
}

