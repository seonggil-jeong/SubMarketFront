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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

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

    @PostMapping("/seller/join")
    public String insertUser(SellerDto sellerDto, ModelMap model, HttpSession session) throws Exception {
        try {
            String url = env.getProperty("gateway.ip") + "/seller-service/sellers";

            String bnUrl = env.getProperty("gateway.ip") + "/seller-service/seller/business/" + sellerDto.getBusinessId();
            ResponseEntity<Map> bnResponse = restTemplate.exchange(bnUrl, HttpMethod.GET, null, Map.class);

            if (bnResponse.getBody().get("b_stt_cd").toString().length() < 1) {
                model.addAttribute("msg", "사업자 번호가 유효하지 않습니다");
                model.addAttribute("url", "/seller/regForm");

                return "/redirect";
            }

            HttpEntity<SellerDto> entity = new HttpEntity<>(sellerDto);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);


            model.addAttribute("msg", response.getBody());
            model.addAttribute("url", "/index");


        } catch (HttpStatusCodeException statusCodeException) {
            int code = statusCodeException.getRawStatusCode();

            if (code == 409) {
                model.addAttribute("msg", statusCodeException.getResponseBodyAsString());
                model.addAttribute("url", "/seller/regForm");
            } else {
                model.addAttribute("msg", "Server Error");
                model.addAttribute("url", "/seller/regForm");
            }
        } catch (Exception exception) {
            model.addAttribute("msg", "Server Error");
            model.addAttribute("/index");

        } finally {
            return "/redirect";
        }

    }

    @PostMapping("/sellers/modify")
    public String sellerModify(SellerDto sellerDto, ModelMap model, HttpSession session) throws Exception {
        String token = (String) session.getAttribute("SS_SELLER_TOKEN");
        String url = env.getProperty("gateway.ip") + "/seller-service/sellers/modify";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);

            HttpEntity<SellerDto> entity = new HttpEntity(sellerDto, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            SellerDto sellerInfo = sellerService.getSellerInfo(token);

            session.setAttribute("SS_SELLER_INFO", sellerInfo);

            model.addAttribute("msg", response.getBody());
            model.addAttribute("url", "/seller/profile");

        } catch (Exception e) {
            log.info("Exception : " + e);
            model.addAttribute("msg", "Server Error");
            model.addAttribute("url", "/seller/profile");
        }finally {
            return "/redirect";
        }
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
