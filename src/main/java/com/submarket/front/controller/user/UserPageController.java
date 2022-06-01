package com.submarket.front.controller.user;

import com.submarket.front.dto.SubDto;
import com.submarket.front.service.impl.UserService;
import com.submarket.front.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserPageController {
    private final UserService userService;

    /**
     * page 이동 Controller
     * @return pageInfo
     */

    @RequestMapping("/user/profile")
    public String userProfile(HttpSession session) throws Exception {
            return "/user/page-profile";
    }

    @RequestMapping("/user/sublist")
    public String subList(HttpSession session, ModelMap model) throws Exception {
        String token = String.valueOf(session.getAttribute("SS_USER_TOKEN"));
        List<SubDto> subDtoList = userService.getSubList(token);
        model.addAttribute("subDtoList", subDtoList);
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
