package com.submarket.front.controller.seller;

import com.submarket.front.dto.ItemDto;
import com.submarket.front.dto.SalesDto;
import com.submarket.front.dto.SellerDto;
import com.submarket.front.service.impl.ItemService;
import com.submarket.front.service.impl.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SellerPageController {
    private final SellerService sellerService;

    @RequestMapping("/seller/main")
    public String sellerMain(HttpSession session, ModelMap model) throws Exception {
        SellerDto sellerDto = (SellerDto) session.getAttribute("SS_SELLER_INFO");
        String token = String.valueOf(session.getAttribute("SS_SELLER_TOKEN"));
        List<ItemDto> itemDtoList = sellerService.findItemList(token);
        int totalPrice = sellerService.findTotalValue(token, itemDtoList);

        List<SalesDto> salesDtoList = sellerService.findAllSalesDtoBySellerId(token);

        model.addAttribute("salesDtoList", salesDtoList);
        model.addAttribute("itemDtoList", itemDtoList);
        model.addAttribute("totalPrice", totalPrice);
        if (sellerDto == null) {
            return "/index";
        }

        return "/seller/page-main";
    }

    @RequestMapping("/seller/item")
    public String sellerItemAddPage(HttpSession session) throws Exception {
        return "/seller/page-add-item";
    }

    @RequestMapping("/seller/profile")
    public String sellerProfile(HttpSession session) throws Exception {
        return "/seller/page-profile";
    }

    @RequestMapping("/seller/my-item")
    public String sellerMyItem(HttpSession session) throws Exception {
        return "/seller/page-my-item";
    }
}
