package com.submarket.front.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemPageController {

    @RequestMapping("/items")
    public String getItemInfo(ModelMap model) throws Exception {
        model.addAttribute("title", "모든 상품 보기");
        return "/page-items";
    }

    @RequestMapping("/items/category/{categorySeq}")
    public String getItemInfoByCategory(@PathVariable int categorySeq, ModelMap model, HttpSession session)
            throws Exception {

        return "/index";

    }


}
