package com.example.demo.global.oauth;

import com.example.demo.domain.user.domain.ProviderType;
import com.example.demo.domain.user.domain.User;
import com.example.demo.domain.user.domain.repository.UserRepository;
import com.example.demo.global.oauth.info.OAuthUserInfo;
import com.example.demo.global.oauth.info.OAuthUserInfoFactory;
import com.example.demo.global.oauth.info.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomOAuthUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User user = oAuth2UserService.loadUser(userRequest);

        return this.process(userRequest, user);
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

        ProviderType providerType = ProviderType.valueOf(
                userRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OAuthUserInfo userInfo = OAuthUserInfoFactory.getOAuth2UserInfo(providerType, oAuth2User.getAttributes());

        User user = userRepository.findByEmail(userInfo.getEmail())
                .map(entity -> {
                    if (userInfo.getEmail() != null && !entity.getName().equals(userInfo.getEmail())) {
                        entity.updateName(userInfo.getName());
                    }
                    return userRepository.save(entity);
                })
                .orElseGet(() -> {
                   return userRepository.save(
                            User.builder()
                                    .name(userInfo.getName())
                                    .email(userInfo.getEmail())
                                    .providerType(providerType)
                                    .build());
                });

        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("USER")),
                userInfo.getAttributes(),
                userInfo.getNameAttributeKey());
    }
}
