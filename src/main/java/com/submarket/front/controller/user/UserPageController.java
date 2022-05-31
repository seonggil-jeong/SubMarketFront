package com.submarket.front.controller.user;

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
     * @return pageInfo
     */

    @RequestMapping("/user/profile")
    public String userProfile(HttpSession session) throws Exception {
            return "/user/page-profile";
    }

    @RequestMapping("/user/sublist")
    public String subList(HttpSession session) {
        return "/user/page-sublist";
    }

    @RequestMapping("/user/reviewlist")
    public String reviewList(HttpSession session) {
        return "/user/page-reviewlist";
    }

    @RequestMapping("/user/page-login")
    public String userPageLogin(HttpSession session) {
        return "/user/page-login";
    }
}
