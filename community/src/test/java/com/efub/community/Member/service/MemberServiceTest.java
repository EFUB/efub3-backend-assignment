package com.efub.community.Member.service;

import com.efub.community.Member.domain.Member;
import com.efub.community.Member.domain.RefreshToken;
import com.efub.community.Member.dto.SignInResponseDto;
import com.efub.community.Member.repository.MemberRepository;
import com.efub.community.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class MemberServiceTest {
    @InjectMocks
    private MemberService memberService;

    @Mock
    private RefreshTokenService refreshTokenService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private BCryptPasswordEncoder encoder;

    private long expiredTime = 1000 * 60 * 60L;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        encoder = new BCryptPasswordEncoder();
        memberService = new MemberService(memberRepository, refreshTokenService, encoder);
        memberService.accessKey = "testAccessKey";
        memberService.refreshKey = "testRefreshKey";
    }

    @Test
    void signUpSuccess() {
        when(memberRepository.existsByEmail(anyString())).thenReturn(false);

        String response = memberService.signup("test@email.com", "password12", "univ", "nickname", "studentId");
        assertEquals("회원 가입이 완료되었습니다.", response);
    }

    @Test
    void signUpFailure() {
        when(memberRepository.existsByEmail(anyString())).thenReturn(true);

        memberService.signup("test@email.com", "password", "univ", "nickname", "studentId");
    }

    @Test
    void signInSuccess() {
        // Mocked member with the encoded password
        String rawPassword = "password";
        String encodedPassword = encoder.encode(rawPassword);
        Member mockMember = new Member("test@email.com", encodedPassword, "nickname", "univ", "studentId");

        when(memberRepository.findMemberByEmail(anyString())).thenReturn(Optional.of(mockMember));
        when(encoder.matches(eq(rawPassword), anyString())).thenReturn(true);
        assertNotNull(memberService.signin("test@email.com", rawPassword));
    }

    @Test
    void signInFailure() {
        when(memberRepository.findMemberByEmail(anyString())).thenReturn(Optional.empty());

        memberService.signin("test@email.com", "password");
    }

    @Test
    void findMemberSuccess() {
        Member mockMember = new Member("test@email.com", "password", "nickname", "univ", "studentId");
        when(memberRepository.findMemberByEmail(anyString())).thenReturn(Optional.of(mockMember));

        assertNotNull(memberService.findMemberByEmail("test@email.com"));
    }

    @Test
    void findMemberFailure() {
        when(memberRepository.findMemberByEmail(anyString())).thenReturn(Optional.empty());

        memberService.findMemberByEmail("test@email.com");
    }

    @Test
    void requestRefreshTokenSuccess() {
        String givenRefreshToken = "some.Valid.RefreshToken";
        String userEmail = "test@email.com";
        String newAccessToken = "someValidAccessToken";

        RefreshToken foundRefreshToken = new RefreshToken();
        foundRefreshToken.setValue(givenRefreshToken);
        foundRefreshToken.setEmail(userEmail);

        Claims mockClaims = Jwts.claims();
        mockClaims.put("email", userEmail);

        when(refreshTokenService.findRefreshToken(givenRefreshToken)).thenReturn(foundRefreshToken);
        when(jwtUtil.parseRefreshToken(givenRefreshToken, memberService.refreshKey)).thenReturn(mockClaims);
        when(jwtUtil.createAccessToken(userEmail, memberService.accessKey, expiredTime)).thenReturn(newAccessToken);

        SignInResponseDto responseDto = memberService.requestRefresh(givenRefreshToken);

        assertNotNull(responseDto);
        assertEquals(userEmail, responseDto.getEmail());
        assertEquals(newAccessToken, responseDto.getAccessToken());
    }



    @Test
    void requestRefreshTokenFailure() {
        String invalidRefreshToken = "invalidRefreshToken";

        when(refreshTokenService.findRefreshToken(invalidRefreshToken)).thenThrow(new EntityNotFoundException("존재하지 않는 RefreshToken입니다!"));

        assertThrows(EntityNotFoundException.class, () -> memberService.requestRefresh(invalidRefreshToken));
    }
}