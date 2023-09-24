package com.example.demo.oauth.info;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthUserInfo {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String password;
    private String email;

    @Builder
    public OAuthUserInfo(Map<String, Object> attributes, String nameAttributeKey,
                         String password, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
