package com.efub.community.security.service;

import com.efub.community.domain.member.domain.Member;
import com.efub.community.domain.member.domain.MemberAdapter;
import com.efub.community.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServcieImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다"));

        // UserDetails를 반환한다.
        return new MemberAdapter(member);
    }

}