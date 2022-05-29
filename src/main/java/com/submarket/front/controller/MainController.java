package com.submarket.front.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class MainController {
    @RequestMapping("/")
    public String index(HttpSession session) {
        // TODO: 2022/05/29 사용자 정보가 없다면 Login / Sing Up or User Info
        return "/index";

    }

    @RequestMapping("/itemlist")
    public String itemList() {
        return "/item-list";
    }

    @RequestMapping("itemdetails")
    public String itemDetails() {
        return "/item-details";
    }
}
