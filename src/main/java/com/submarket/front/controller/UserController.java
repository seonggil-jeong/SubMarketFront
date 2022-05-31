package com.submarket.front.controller;

import com.submarket.front.dto.UserDto;
import com.submarket.front.service.impl.UserService;
import com.submarket.front.util.CmmUtil;
import com.submarket.front.vo.RequestLogin;
import com.submarket.front.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final Environment env;
    private final RestTemplate restTemplate;
    private final UserService userService;
    @RequestMapping("/user/sign-up")
    public String userInfo(HttpServletResponse response) {
        log.info("go to user sign-up");
        return "/user/user-reg";
    }

    @RequestMapping("/user/regForm")
    public String userRegForm() throws Exception {
        return "/user/user-reg";
    }
    @RequestMapping("/user/profile")
    public String userProfile(HttpSession session) throws Exception {
        if (CmmUtil.nvl((String) session.getAttribute("TOKEN")).length() > 1) {
            return "/user/user-profile";
        } else {

        return "/user/user-login";
        }
    }

    @PostMapping("/user/modifyUserInfo")
    public String modifyUSerInfo(HttpSession session, UserDto userDto, ModelMap model) throws Exception {
        String token = session.getAttribute("TOKEN").toString();
        userDto.setToken(token);
        log.info("userEmail : " + userDto.getUserEmail());

        String resultString = userService.modifyUserInfo(userDto);
        log.info("result : " + resultString);

        UserDto rUserDto = userService.getUserInfo((String) session.getAttribute("TOKEN"));
        session.setAttribute("SS_USERINFO", rUserDto);

        model.addAttribute("msg", resultString);
        model.addAttribute("url", "/user/profile");
        return "/redirect";
    }

    @PostMapping("/user/changePassword")
    public String changePassword(HttpServletRequest request) throws Exception {
        // TODO: 2022-05-30 사용자 비밀번호 변경
        return null;
    }







    // 사용자 로그인
    @PostMapping("/user/login")
    public String userLogin(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

        log.info(this.getClass().getName() + ".login Start!");
        String url = env.getProperty("gateway.ip") + "/user-service/login";

        try {
            String userId = CmmUtil.nvl(request.getParameter("userId"));
            String userPassword = CmmUtil.nvl(request.getParameter("userPassword"));
            HttpHeaders headers = new HttpHeaders();

            RequestLogin body = new RequestLogin();
            body.setUserId(userId);
            body.setUserPassword(userPassword);

            HttpEntity<RequestLogin> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);


            UserDto userDto = userService.getUserInfo(response.getHeaders().get("token").get(0));

            session.setAttribute("TOKEN", response.getHeaders().get("token").get(0));
            session.setAttribute("SS_USERINFO", userDto);

            model.addAttribute("msg", "환영합니다");
            model.addAttribute("url", "/index");

        } catch (Exception e) { // 로그인 오류
            model.addAttribute("msg", "비밀번호 또는 아이디를 확인해 주세요");
            model.addAttribute("url", "/index");

        }


        return "/redirect";
    }

}
