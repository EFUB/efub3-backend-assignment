package com.efub.community.security;


import com.efub.community.domain.member.domain.Member;
import java.util.Map;

public interface OAuth2UserInfo {
    String getProviderId();

    String getRegistrationId();

    Map<String, Object> getAttributes();

    String getEmail();
    String getNickname();
    String getProfileImageUrl();

    String getName();


    public Member toEntity();
}
