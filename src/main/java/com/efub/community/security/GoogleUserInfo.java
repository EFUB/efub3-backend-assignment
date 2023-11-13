package com.efub.community.security;

import com.efub.community.domain.member.domain.Member;
import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo{
    public static final String REGISTRATION_ID = "google";

    private static final String PROVIDER_ID = "sub";

    private static final String EMAIL = "email";
    private static final String PROFILE = "picture";

    private Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return attributes.get(PROVIDER_ID).toString();
    }

    @Override
    public String getRegistrationId() {
        return REGISTRATION_ID;
    }

    @Override
    public String getEmail() {
        return attributes.get(EMAIL).toString();
    }

    @Override
    public String getNickname() {
        String email = getEmail();
        return email.substring(0, email.indexOf("@"));
    }

    @Override
    public String getName() {
        return REGISTRATION_ID + "_" + getProviderId();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProfileImageUrl(){
        return attributes.get(PROFILE).toString();
    }

    @Override
    public Member toEntity() {
        return Member.builder()
                .email(this.getEmail())
                .nickname(this.getNickname())
                .build();
    }

}