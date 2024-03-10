package com.study.sos_backend.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    ADMIN("ADMIN"),
    BUSINESS("BUSINESS"),
    USER("USER");

    private final String code;


}
