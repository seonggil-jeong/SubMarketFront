package com.submarket.front.controller.item;

import com.submarket.front.dto.ItemDto;
import com.submarket.front.dto.ItemReviewDto;
import com.submarket.front.dto.SubDto;
import com.submarket.front.dto.UserDto;
import com.submarket.front.service.impl.ItemService;
import com.submarket.front.service.impl.UserService;
import com.submarket.front.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final Environment env;
    private final UserService userService;
    private final RestTemplate restTemplate;

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
        log.info("itemReview Contents : " + itemReviewDto.getReviewContents());
        String token = (String) session.getAttribute("SS_USER_TOKEN");
        UserDto userDto = (UserDto) session.getAttribute("SS_USER_INFO");
        itemReviewDto.setUserId(userDto.getUserId());

        String rStr = itemService.modifyItemReviewByReviewSeq(itemReviewDto, token);

        model.addAttribute("msg", rStr);
        model.addAttribute("url", "/user/reviewlist");

        return "/redirect";
    }

    @PostMapping("/user/review/{itemSeq}/create")
    public String createReview(HttpSession session, ModelMap model, ItemReviewDto itemReviewDto, @PathVariable int itemSeq) throws Exception {

        UserDto userDto = (UserDto) session.getAttribute("SS_USER_INFO");
        String tokenC = String.valueOf(session.getAttribute("SS_USER_TOKEN"));
        itemReviewDto.setUserAge(userDto.getUserAge());
        itemReviewDto.setUserId(userDto.getUserId());

        if (tokenC.length() < 5) {
            model.addAttribute("msg", "로그인 후 이용 가능합니다.");
            model.addAttribute("url", "/user/page-login");
        }
            
        try {
            RestTemplate restTemplate = new RestTemplate();
            String token = CmmUtil.nvl(String.valueOf(session.getAttribute("SS_USER_TOKEN")));
            String url = env.getProperty("gateway.ip") + "/item-service/item/" + itemSeq + "/review";
            List<SubDto> subDtoList = userService.getSubList(token);
            for (SubDto subDto : subDtoList) {
                int itemSeqInSubDto = subDto.getItemSeq();

                if (itemSeq == itemSeqInSubDto) {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", token);

                    HttpEntity<ItemReviewDto> entity = new HttpEntity<>(itemReviewDto, headers);
                    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

                    model.addAttribute("msg", response.getBody());
                    model.addAttribute("url", "/items/" + itemSeq);
                    return "/redirect";
                }
            }

            model.addAttribute("msg", "상품을 구독 후 리뷰를 작성할 수 있습니다");
            model.addAttribute("url", "/items/" + itemSeq);
            

        } catch (HttpStatusCodeException statusCodeException) {
            log.info("HttpStatusCodeException : ", statusCodeException);
            int code = statusCodeException.getRawStatusCode();

            if (code == 400) {
                model.addAttribute("msg", statusCodeException.getResponseBodyAsString());
                model.addAttribute("url", "/user/reviewlist");
            } else {
                model.addAttribute("msg", "로그인 정보를 확인해 주세요");
                model.addAttribute("url", "/index");
            }

        } catch (Exception e) {
            log.info("Exception : " + e);
            model.addAttribute("msg", "로그인 정보를 확인해 주세요");
            model.addAttribute("url", "/index");
        }
        return "/redirect";
    }

    @RequestMapping("/items/off/{itemSeq}")
    public String itemOff(HttpSession session, @PathVariable int itemSeq, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + ".itemOff Start!");

        String token = String.valueOf(session.getAttribute("SS_SELLER_TOKEN"));

        String url = env.getProperty("gateway.ip") + "/item-service/items/" + itemSeq + "/off";
        log.info("url : " + url);
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", token);

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        model.addAttribute("msg", response.getBody());
        model.addAttribute("url", "/seller/my-item");


        log.info(this.getClass().getName() + ".itemOff End!");

        return "/redirect";
    }

    @RequestMapping("/items/on/{itemSeq}")
    public String itemOn(HttpSession session, @PathVariable int itemSeq, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + ".itemOn Start!");

        String token = String.valueOf(session.getAttribute("SS_SELLER_TOKEN"));

        String url = env.getProperty("gateway.ip") + "/item-service/items/" + itemSeq + "/on";

        log.info("url : " + url);

        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", token);

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        model.addAttribute("msg", response.getBody());
        model.addAttribute("url", "/seller/my-item");


        log.info(this.getClass().getName() + ".itemOn End!");

        return "/redirect";
    }

    @PostMapping("/seller/items/{itemSeq}/modify")
    public String modifyItemInfo(@PathVariable int itemSeq, ItemDto itemDto, ModelMap model, HttpSession session) throws Exception {
        log.info(this.getClass().getName() + ".modifyItemInfo Start!");

        String token = String.valueOf(session.getAttribute("SS_SELLER_TOKEN"));
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        String url = env.getProperty("gateway.ip") + "/item-service/items/modify/" + itemSeq;

        log.info("mainSize : " + itemDto.getMainImage().getSize());
        log.info("subSize : " + itemDto.getSubImage().getSize());


        try {
            if (itemDto.getMainImage().getSize() > 1) {
                ByteArrayResource mainImage = new ByteArrayResource(itemDto.getMainImage().getBytes()) {
                    @Override
                    public String getFilename() {
                        return itemDto.getMainImage().getOriginalFilename();
                    }
                };

                body.add("mainImage", mainImage);
            }

            if (itemDto.getSubImage().getSize() > 1) {
                ByteArrayResource subImage = new ByteArrayResource(itemDto.getSubImage().getBytes()) {
                    @Override
                    public String getFilename() {
                        return itemDto.getSubImage().getOriginalFilename();
                    }
                };

                body.add("subImage", subImage);

            }

            body.add("itemTitle", itemDto.getItemTitle());
            body.add("itemContents", itemDto.getItemContents());
            body.add("itemPrice", itemDto.getItemPrice());
            body.add("itemCount", itemDto.getItemPrice());
            body.add("categorySeq", itemDto.getCategorySeq());
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);

            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity(body, headers);
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);


            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            model.addAttribute("msg", response.getBody());
            model.addAttribute("url", "/seller/my-item");
        } catch (HttpStatusCodeException statusCodeException) {
            log.info("HttpStatusCodeException : " + statusCodeException);
            int code = statusCodeException.getRawStatusCode();

            model.addAttribute("msg", "상품 정보 수정 실패");
            model.addAttribute("url", "/seller/my-item");
        } catch (Exception exception) {
            log.info("Exception : " + exception);

            model.addAttribute("msg", "Server Error");
            model.addAttribute("url", "/index");
        }

        log.info(this.getClass().getName() + ".modifyItemInfo End!");


        return "/redirect";
    }

}
