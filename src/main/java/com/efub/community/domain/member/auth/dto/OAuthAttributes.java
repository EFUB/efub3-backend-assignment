package com.efub.community.domain.member.auth.dto;

import java.util.Map;

import com.efub.community.domain.member.domain.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {

	private final Map<String, Object> attributes;
	private final String nameAttributeKey;
	private final String email;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String email) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.email = email;
	}

	public static OAuthAttributes of(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
			.email((String)attributes.get("email"))
			.attributes(attributes)
			.nameAttributeKey(userNameAttributeName)
			.build();
	}

	public Member toEntity() {
		return Member.builder()
			.email(email)
			.build();
	}
}
