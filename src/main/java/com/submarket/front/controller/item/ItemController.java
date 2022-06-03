package com.submarket.front.controller.item;

import com.submarket.front.dto.ItemReviewDto;
import com.submarket.front.service.impl.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping("/items/{itemSeq}")
    public String findOneItemInfo(@PathVariable int itemSeq) throws Exception {
        log.info(this.getClass().getName() + ".findOneItemInfo Start!");


        return null;
    }


    @RequestMapping("/user/review/delete/{reviewSeq}")
    public String deleteReview(HttpServletRequest request, HttpSession session, ModelMap model,
                               @PathVariable int reviewSeq) throws Exception {

        String token = (String) session.getAttribute("SS_USER_TOKEN");

        String rStr = itemService.deleteItemReviewByReviewSeq(reviewSeq, token);

        model.addAttribute("msg", rStr);
        model.addAttribute("url", "/user/reviewlist");

        return "/redirect";
    }

    @PostMapping("/user/review/modify")
    public String modifyReview(HttpSession session, ItemReviewDto itemReviewDto, ModelMap model) throws Exception {
        String token = (String) session.getAttribute("SS_USER_TOKEN");

        String rStr = itemService.modifyItemReviewByReviewSeq(itemReviewDto, token);

        model.addAttribute("msg", rStr);
        model.addAttribute("url", "/user/reviewlist");

        return "/redirect";
    }

}
