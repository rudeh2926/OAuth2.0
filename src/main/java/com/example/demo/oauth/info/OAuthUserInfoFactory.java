package com.example.demo.oauth.info;

import com.example.demo.domain.user.domain.ProviderType;
import com.example.demo.oauth.info.impl.GoogleOAuthUserInfo;
import com.example.demo.oauth.info.impl.KakaoOAuthUserInfo;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class OAuthUserInfoFactory {
    public static OAuthUserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {

        switch (providerType) {
            case GOOGLE:
                return GoogleOAuthUserInfo.ofGoogle(attributes);
            case KAKAO:
                return KakaoOAuthUserInfo.ofKakao(attributes);
            default:
                throw new IllegalArgumentException("Not");
        }
    }
}
