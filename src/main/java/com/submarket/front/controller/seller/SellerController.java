package com.submarket.front.controller.seller;

import com.submarket.front.dto.ItemDto;
import com.submarket.front.dto.SellerDto;
import com.submarket.front.service.impl.SellerService;
import com.submarket.front.vo.RequestSellerLogin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SellerController {
    private final Environment env;
    private final RestTemplate restTemplate;
    private final SellerService sellerService;



    @PostMapping("/seller/item/add")
    public String sellerItemAdd(HttpSession session, ItemDto itemDto, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "sellerIteAdd Start!");
        String token = (String) session.getAttribute("SS_SELLER_TOKEN");

        String rStr = sellerService.addItem(token, itemDto);

        log.info(this.getClass().getName() + "sellerIteAdd End!");

        model.addAttribute("msg", rStr);
        model.addAttribute("url", "/index");


        return "/redirect";
    }




    @PostMapping("/seller/login")
    public String sellerLogin(HttpSession session, RequestSellerLogin requestSellerLogin, ModelMap model) throws IOException {
        String url = env.getProperty("gateway.ip") + "/seller-service/login";

        try {
            HttpHeaders headers = new HttpHeaders();

            HttpEntity<RequestSellerLogin> entity = new HttpEntity<>(requestSellerLogin, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            String token = response.getHeaders().get("token").get(0);
            session.setAttribute("SS_SELLER_TOKEN", token);

            // TODO: 2022-05-31 사용자 정보 세션에 저장하는 로직 추가

            model.addAttribute("msg", "로그인 성공");
            model.addAttribute("url", "/seller/main");

            SellerDto sellerDto = sellerService.getSellerInfo(token);
            session.setAttribute("SS_SELLER_INFO", sellerDto);

        } catch (HttpStatusCodeException statusCodeException) {

            int code = statusCodeException.getRawStatusCode();

            if (code < 500 || code > 400) {
                // 사업자 비밀번호 or password Error
                model.addAttribute("msg", "비밀번호 또는 아이디가 일치하지 않습니다");
                model.addAttribute("url", "/index");
            }


        } catch (Exception e) {
            model.addAttribute("msg", "Server Error 관리자에게 문의하세요");
            model.addAttribute("url", "/index");

        } finally {
            return "/redirect";
        }
    }
}
