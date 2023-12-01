package com.efub.community.domain.member.auth.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.efub.community.domain.member.domain.Member;
import com.efub.community.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return memberRepository.findByEmail(email)
			.map(this::createUserDetails)
			.orElseThrow(() -> new UsernameNotFoundException("User not found. email: " + email));
	}

	private UserDetails createUserDetails(Member member) {
		return User.builder()
			.username(member.getEmail())
			.password(member.getEncodedPassword())
			.roles("USER")
			.build();
	}
}
