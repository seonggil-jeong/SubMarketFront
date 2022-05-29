package com.submarket.front.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class UserController {

    @RequestMapping("/user/sign-up")
    public String userInfo(HttpServletResponse response) {
        log.info("go to user sign-up");
        return "/user/user-reg";
    }

    @RequestMapping("/user/profile")
    public String userProfile() {
        return "user/user-profile";
    }
}
