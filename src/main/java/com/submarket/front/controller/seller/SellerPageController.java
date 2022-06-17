package com.submarket.front.controller.seller;

import com.submarket.front.dto.ItemDto;
import com.submarket.front.dto.OrderDto;
import com.submarket.front.dto.SalesDto;
import com.submarket.front.dto.SellerDto;
import com.submarket.front.service.impl.ItemService;
import com.submarket.front.service.impl.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SellerPageController {
    private final SellerService sellerService;
    private final ItemService itemService;

    @RequestMapping("/seller/main")
    public String sellerMain(HttpSession session, ModelMap model) throws Exception {
        SellerDto sellerDto = (SellerDto) session.getAttribute("SS_SELLER_INFO");
        String token = String.valueOf(session.getAttribute("SS_SELLER_TOKEN"));
        List<ItemDto> itemDtoList = sellerService.findItemList(token);
        int totalPrice = sellerService.findTotalValue(token, itemDtoList);

        List<SalesDto> salesDtoList = sellerService.findAllSalesDtoBySellerId(token);

        List<OrderDto> orderDtoList = sellerService.getOrderDtoList(sellerDto.getSellerId(), token);

        model.addAttribute("salesDtoList", salesDtoList);
        model.addAttribute("itemDtoList", itemDtoList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("orderDtoList", orderDtoList);


        if (sellerDto == null) {
            return "/index";
        }

        return "/seller/page-main";
    }

    @RequestMapping("/seller/order")
    public String sellerOrder(HttpSession session, ModelMap model) throws Exception {
        SellerDto sellerDto = (SellerDto) session.getAttribute("SS_SELLER_INFO");
        String token = String.valueOf(session.getAttribute("SS_SELLER_TOKEN"));

        List<OrderDto> orderDtoList = sellerService.getOrderDtoList(sellerDto.getSellerId(), token);
        model.addAttribute("orderDtoList", orderDtoList);

        return "/seller/page-order";
    }

    @RequestMapping("/seller/item")
    public String sellerItemAddPage(HttpSession session ,ModelMap model) throws Exception {

        return "/seller/page-add-item";
    }

    @RequestMapping("/seller/profile")
    public String sellerProfile(HttpSession session) throws Exception {
        return "/seller/page-profile";
    }

    @RequestMapping("/seller/my-item")
    public String sellerMyItem(HttpSession session, ModelMap model) throws Exception {
        String token = String.valueOf(session.getAttribute("SS_SELLER_TOKEN"));

        List<ItemDto> itemDtoList = sellerService.findItemList(token);
        itemDtoList = sellerService.findEachItemTotalPrice(token, itemDtoList);

        model.addAttribute("itemDtoList", itemDtoList);
        return "/seller/page-my-item";
    }

    @RequestMapping("/seller/item/{itemSeq}/modify")
    public String sellerModifyItem(@PathVariable int itemSeq, ModelMap model) throws Exception {
        ItemDto itemDto = itemService.getItemInfoDetails(itemSeq);

        model.addAttribute("itemDto", itemDto);

        return "/seller/page-modify-item";
    }
}
