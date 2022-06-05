package com.submarket.front.controller.seller;

import com.submarket.front.dto.SellerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class SellerPageController {
    @RequestMapping("/seller/main")
    public String sellerMain(HttpSession session) throws Exception {
        SellerDto sellerDto = (SellerDto) session.getAttribute("SS_SELLER_INFO");

        if (sellerDto == null) {
            return "/index";
        }

        return "/seller/page-main";
    }

    @RequestMapping("/seller/item")
    public String sellerItemAddPage(HttpSession session) throws Exception {
        return "/seller/page-add-item";
    }
}
