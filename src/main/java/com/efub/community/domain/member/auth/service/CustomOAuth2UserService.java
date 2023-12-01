package com.efub.community.domain.member.auth.service;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.efub.community.domain.member.auth.dto.OAuthAttributes;
import com.efub.community.domain.member.auth.dto.SessionUser;
import com.efub.community.domain.member.domain.Member;
import com.efub.community.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final MemberRepository memberRepository;
	private final HttpSession httpSession;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);

		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
			.getUserInfoEndpoint().getUserNameAttributeName();

		OAuthAttributes attributes = OAuthAttributes.of(userNameAttributeName, oAuth2User.getAttributes());

		Member member = saveOrUpdate(attributes);

		httpSession.setAttribute("USER", new SessionUser(member));

		return new DefaultOAuth2User(
			Collections.singleton(new SimpleGrantedAuthority("USER")),
			attributes.getAttributes(),
			attributes.getNameAttributeKey()
		);
	}

	private Member saveOrUpdate(OAuthAttributes attributes) {
		Member member = memberRepository.findByEmail(attributes.getEmail())
			.orElse(attributes.toEntity());
		return memberRepository.save(member);
	}
}
