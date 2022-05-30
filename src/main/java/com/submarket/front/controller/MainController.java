package com.submarket.front.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Controller
public class MainController {
    @RequestMapping("/index")
    public String index(HttpSession session) {
        // TODO: 2022/05/29 사용자 정보가 없다면 Login / Sing Up or User Info

        return "/index";

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/index";
    }
}
