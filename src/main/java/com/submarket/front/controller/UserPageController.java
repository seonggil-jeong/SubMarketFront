package com.submarket.front.controller;

import com.submarket.front.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class UserPageController {

    /**
     * page 이동 Controller
     * @param response
     * @return pageInfo
     */




    @RequestMapping("/user/sign-up")
    public String userInfo(HttpServletResponse response) {
        log.info("go to user sign-up");
        return "/user/user-reg";
    }

    @RequestMapping("/user/profile")
    public String userProfile(HttpSession session) throws Exception {
            return "/user/user-profile";
    }

    @RequestMapping("/user/sub-list")
    public String subList(HttpSession session) {
        return "/user/sub-list";
    }

    @RequestMapping("/user/userLogin")
    public String userUserLogin(HttpSession session) {
        return "/user/user-login";
    }
}
