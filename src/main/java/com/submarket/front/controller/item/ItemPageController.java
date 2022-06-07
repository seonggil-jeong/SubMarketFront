package com.submarket.front.controller.item;

import com.submarket.front.dto.*;
import com.submarket.front.service.impl.ItemService;
import com.submarket.front.service.impl.SellerService;
import com.submarket.front.service.impl.UserService;
import com.submarket.front.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemPageController {
    private final ItemService itemService;
    private final UserService userService;
    private final SellerService sellerService;

    @RequestMapping("/items")
    public String getItemInfo(ModelMap model) throws Exception {
        model.addAttribute("title", "모든 상품 보기");
        List<ItemDto> itemDtoList = itemService.getItemInfo();

        model.addAttribute("itemDtoList", itemDtoList);

        return "/page-items";
    }


    @RequestMapping("/items/category/{categorySeq}")
    public String getItemInfoByCategory(@PathVariable int categorySeq, ModelMap model, HttpSession session)
            throws Exception {
        CategoryDto categoryDto = itemService.getItemInfoByCategorySeq(categorySeq);

        if (categoryDto == null) {
            categoryDto = new CategoryDto();
        }
        String title = categoryDto.getCategoryName();

        List<ItemDto> itemDtoList = categoryDto.getItems();


        model.addAttribute("title", title);
        model.addAttribute("itemDtoList", itemDtoList);


        return "/page-items";

    }

    @RequestMapping("/items/group/{groupSeq}")
    public String getItemInfoByGroup(@PathVariable int groupSeq, ModelMap model, HttpSession session) throws Exception {
        // TODO: 2022-06-02 GroupSeq 로 상품 목록 조회

        return "/index";

    }


    @RequestMapping("/items/{itemSeq}")
    public String getItemInfoDetails(ModelMap model, HttpSession session, @PathVariable int itemSeq) throws Exception {
        ItemDto itemDto = itemService.getItemInfoDetails(itemSeq);
        List<ItemReviewDto> itemReviewDtoList = itemService.findItemReviewByItemSeq(itemSeq);
        // TODO: 2022-06-07 리뷰 정보 불러오기

        model.addAttribute("itemDto", itemDto);
        model.addAttribute("itemReviewDtoList", itemReviewDtoList);

        return "/page-item-details";
    }


}
