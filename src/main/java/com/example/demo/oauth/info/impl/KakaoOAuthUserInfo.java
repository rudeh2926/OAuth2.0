package com.example.demo.oauth.info.impl;

import com.example.demo.oauth.info.OAuthUserInfo;

import java.util.Map;

public class KakaoOAuthUserInfo extends OAuthUserInfo {
    public KakaoOAuthUserInfo (Map<String, Object> attributes, String nameAttributeKey, String name, String password, String email) {
        super(attributes, nameAttributeKey, name, password, email);
    }

    public static OAuthUserInfo ofKakao(Map<String, Object> attributes) {
        return KakaoOAuthUserInfo.builder()
                .name((String) attributes.get("name"))
                .password((String) attributes.get("password"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey("sub")
                .build();
    }
}
