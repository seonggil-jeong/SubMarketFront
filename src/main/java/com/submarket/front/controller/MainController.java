package com.submarket.front.controller;

import com.submarket.front.dto.ItemDto;
import com.submarket.front.service.impl.ItemService;
import com.submarket.front.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final ItemService itemService;

    @RequestMapping("/")
    public String root(ModelMap model) throws Exception {
        List<ItemDto> itemDtoList = itemService.findItemRandomItem();
        model.addAttribute("itemDtoList", itemDtoList);

        return "/index";

    }

    @RequestMapping("/index")
    public String index(ModelMap model) throws Exception {
        List<ItemDto> itemDtoList = itemService.findItemRandomItem();
        model.addAttribute("itemDtoList", itemDtoList);



        return "/index";

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, ModelMap model) throws Exception {
        session.invalidate();
        List<ItemDto> itemDtoList = itemService.findItemRandomItem();

        model.addAttribute("itemDtoList", itemDtoList);

        return "/index";
    }
}
