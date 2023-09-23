package com.example.demo.global.oauth.info.dto;

import com.example.demo.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SessionUser {
    private String name;
    private String password;
    private String email;

    public SessionUser(User user) {
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }
}
