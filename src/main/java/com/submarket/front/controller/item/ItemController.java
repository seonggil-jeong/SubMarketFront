package com.submarket.front.controller.item;

import com.submarket.front.dto.ItemReviewDto;
import com.submarket.front.dto.SubDto;
import com.submarket.front.service.impl.ItemService;
import com.submarket.front.service.impl.UserService;
import com.submarket.front.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

    @PostMapping("/user/review/{itemSeq}/create")
    public String createReview(HttpSession session, ModelMap model, ItemReviewDto itemReviewDto, @PathVariable int itemSeq) throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String token = CmmUtil.nvl(String.valueOf(session.getAttribute("SS_USER_TOKEN")));
            String url = env.getProperty("gateway.ip") + "/item-service/item/" + itemSeq + "/review";
            List<SubDto> subDtoList = userService.getSubList(token);
            for (SubDto subDto : subDtoList) {
                int itemSeqInSubDto = subDto.getItemSeq();

                if (itemSeq == itemSeqInSubDto) {
                    // TODO: 2022-06-10 리뷰 생성 실행
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
                // TODO: 2022-06-10 이미 있을 경우 상품 리뷰 수정 로직 실행 
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

}
