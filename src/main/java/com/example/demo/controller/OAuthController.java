package com.example.demo.controller;

import com.example.demo.oauth.CustomOAuthUserService;
import com.example.demo.oauth.info.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final HttpSession httpSession;
    private final CustomOAuthUserService customOAuthUserService;

    @GetMapping("/")
    public String index(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
}
