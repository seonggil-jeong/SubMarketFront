package com.submarket.front.controller;

import com.submarket.front.dto.ItemDto;
import com.submarket.front.service.impl.ItemService;
import com.submarket.front.service.impl.UserService;
import com.submarket.front.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final ItemService itemService;

    @RequestMapping({"/index"})
    public String index(ModelMap model) throws Exception {
        List<ItemDto> itemDtoList = itemService.findItemRandomItem();
        model.addAttribute("itemDtoList", itemDtoList);

        return "/index";

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/index";
    }
}
