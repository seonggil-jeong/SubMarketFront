package com.submarket.front.controller;

import com.submarket.front.util.CmmUtil;
import com.submarket.front.vo.RequestLogin;
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

    @RequestMapping("/user/sign-up")
    public String userInfo(HttpServletResponse response) {
        log.info("go to user sign-up");
        return "/user/user-reg";
    }

    @RequestMapping("/user/profile")
    public String userProfile() {
        return "user/user-profile";
    }

    @PostMapping("/user/login")
    public String userLogin(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

        log.info(this.getClass().getName() + ".login Start!");
        String url = "http://127.0.0.1:8000/user-service/login";

        try {

            log.info("url : " + url);
            String userId = CmmUtil.nvl(request.getParameter("userId"));
            String userPassword = CmmUtil.nvl(request.getParameter("userPassword"));

            log.info("userId : " + userId);
            log.info("userPassword : " + userPassword);

            HttpHeaders headers = new HttpHeaders();

            RequestLogin body = new RequestLogin();
            body.setUserId(userId);
            body.setUserPassword(userPassword);

            HttpEntity<RequestLogin> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            model.addAttribute("msg", "환영합니다");
            model.addAttribute("url", "/");
            session.setAttribute("TOKEN", response.getHeaders().get("token"));
            log.info("token : " + session.getAttribute("TOKEN"));
        } catch (Exception e) { // 로그인 오류
            model.addAttribute("msg", "비밀번호 또는 아이디를 확인해 주세요");
            model.addAttribute("url", "/");

        }


        return "/redirect";
    }

}
