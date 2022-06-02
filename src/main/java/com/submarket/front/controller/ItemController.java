package com.submarket.front.controller;

import com.submarket.front.service.impl.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;


    @RequestMapping("/user/review/delete/{reviewSeq}")
    public String deleteReview(HttpServletRequest request, HttpSession session, ModelMap model,
                               @PathVariable int reviewSeq) throws Exception {

        String token = (String) session.getAttribute("SS_USER_TOKEN");

        String rStr = itemService.deleteItemReviewByReviewSeq(reviewSeq, token);

        model.addAttribute("msg", rStr);
        model.addAttribute("url", "/user/reviewlist");

        return "/redirect";
    }

}
