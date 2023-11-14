package com.efub.community.Member.service;

import com.efub.community.Member.domain.Member;
import com.efub.community.Member.domain.RefreshToken;
import com.efub.community.Member.dto.SignInResponseDto;
import com.efub.community.Member.repository.MemberRepository;
import com.efub.community.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final RefreshTokenService refreshTokenService;
    private final BCryptPasswordEncoder encoder;

    // test를 위해 임시로 public으로 설정
    private final long expiredTime = 1000 * 60 * 60L; // 1시간
    private final long refreshExpiredTime = 7 * 24 * 60 * 60L; // 일주일

    @Value("${spring.jwt.secret-key}")
    public String accessKey;

    @Value("${spring.jwt.refresh-key}")
    public String refreshKey;

    @Transactional(readOnly = true)
    public Member findMemberByEmail(String email) {
        return memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("email" + email + "은 존재하지 않습니다!"));
    }

    // 회원가입
    public String signup(String email, String pw, String univ, String nickname, String studentId) {
        if (memberRepository.existsByEmail(email)) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        Member member = Member.builder()
                .email(email)
                .pw(encoder.encode(pw))
                .univ(univ)
                .nickname(nickname)
                .studentId(studentId)
                .build();

        memberRepository.save(member);

        return "회원 가입이 완료되었습니다.";
    }

    // 로그인
    public SignInResponseDto signin(String email, String pw) {
        // 존재하지 않는 이메일
        Optional<Member> foundMember = memberRepository.findMemberByEmail(email);

        if (!foundMember.isPresent()) {
            throw new EntityNotFoundException("존재하지 않는 이메일입니다.");
        }

        // 비밀번호 검증
        Member member = foundMember.get();
        if (!encoder.matches(pw, member.getPw())) {
            throw new RuntimeException("잘못된 비밀번호입니다!");
        }

        // 로그인 성공
        // Token 생성
        String accessToken = JwtUtil.createAccessToken(member.getEmail(), accessKey, expiredTime);
        String refreshToken = JwtUtil.createRefreshToken(member.getEmail(), refreshKey, refreshExpiredTime);

        // Token을 DB에 저장
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setValue(refreshToken);
        refreshTokenEntity.setEmail(member.getEmail());
        refreshTokenService.addRefreshToken(refreshTokenEntity);

        // Token 반환
        return SignInResponseDto.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public SignInResponseDto requestRefresh(String refreshToken) {
        // 유효성 검사
        RefreshToken foundRefreshToken = refreshTokenService.findRefreshToken(refreshToken);

        // Refresh Token에 들어있는 email 값 조회
        Claims claims = JwtUtil.parseRefreshToken(foundRefreshToken.getValue(), refreshKey);
        String email = claims.get("email", String.class);
        System.out.println("Email found in RefreshToken: " + email);

        // 회원 조회
        Member member = findMemberByEmail(email);

        // 새 Access Token 생성
        String accessToken = JwtUtil.createAccessToken(member.getEmail(), accessKey, expiredTime);

        // 새 Access Token과 기존 Refresh Token을 DTO에 담아 반환
        return SignInResponseDto
                .builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // 메서드
    @Transactional(readOnly = true)
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member id " + id + " does not exist!"));
    }
}
