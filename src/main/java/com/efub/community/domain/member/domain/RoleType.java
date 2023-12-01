package com.efub.community.domain.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    MEMBER("MEMBER"),
    ADMIN("ADMIN"),
    GUEST("GUEST");

    private final String role;

    public static RoleType fromString(String role ) {
        for(RoleType r : RoleType.values()){
            if(r.role.equals(role)){
                return r;
            }
        }
        return null;
    }
}