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

    @RequestMapping("/user/info/user-info")
    public String userInfo(HttpServletResponse response) {
        return "/user/info/user_info";
    }

    @RequestMapping("/user/main/user-main")
    public String userMain() {
        return "/user/main/user_main";
    }

    @RequestMapping("/user/setting/user-setting")
    public String userSetting() {
        return "/user/setting/user_setting";
    }
}
