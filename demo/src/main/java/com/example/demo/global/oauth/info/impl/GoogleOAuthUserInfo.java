package com.example.demo.global.oauth.info.impl;

import com.example.demo.global.oauth.info.OAuthUserInfo;

import java.util.Map;

public class GoogleOAuthUserInfo extends OAuthUserInfo {
    public GoogleOAuthUserInfo(Map<String, Object> attributes, String nameAttributeKey, String name, String password, String email) {
        super(attributes, nameAttributeKey, name, password, email);
    }

    public static OAuthUserInfo ofGoogle(Map<String, Object> attributes) {
        return OAuthUserInfo.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .password((String) attributes.get("password"))
                .attributes(attributes)
                .nameAttributeKey("sub")
                .build();
    }
}
