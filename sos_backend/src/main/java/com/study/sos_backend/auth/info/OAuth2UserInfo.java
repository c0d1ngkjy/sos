package com.study.sos_backend.auth.info;

import lombok.Getter;

import java.util.Map;

@Getter
public abstract class OAuth2UserInfo {

    protected static final String RESPONSE = "RESPONSE";
    protected Map<String, Object> attributes;

    protected OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();
}
