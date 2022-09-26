package com.submarket.front.controller.item;

import com.submarket.front.dto.*;
import com.submarket.front.service.impl.ItemService;
import com.submarket.front.service.impl.SellerService;
import com.submarket.front.service.impl.UserService;
import com.submarket.front.util.CmmUtil;
import com.submarket.front.vo.ItemLikedRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemPageController {
    private final ItemService itemService;
    private final UserService userService;
    private final SellerService sellerService;
    private final RestTemplate restTemplate;
    private final Environment env;

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

    @RequestMapping("/items/group/{age}")
    public String getItemInfoByAge(@PathVariable int age, ModelMap model, HttpSession session) throws Exception {
        log.info(this.getClass().getName() + ".getItemInfoByAge Start");

        List<ItemDto> itemDtoList = itemService.getItemInfoByAge(age);

        log.info(this.getClass().getName() + ".getItemInfoByAge End");


        model.addAttribute("itemDtoList", itemDtoList);
        model.addAttribute("title", age + "대 인기 상품");


        return "/page-items";


    }


    @RequestMapping("/items/{itemSeq}")
    public String getItemInfoDetails(ModelMap model, HttpSession session, @PathVariable int itemSeq) throws Exception {

        int isLiked = 0;
        UserDto userDto = new UserDto();
        HttpEntity entity;
        int userAge = 0;
        if (session.getAttribute("SS_USER_INFO") != null) {
            userDto = (UserDto) session.getAttribute("SS_USER_INFO");
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", (String) session.getAttribute("SS_USER_TOKEN"));

            userAge += Integer.parseInt(userDto.getUserAge());
            entity = new HttpEntity(headers);

            final String userId = userDto.getUserId();

            String url = env.getProperty("gateway.ip") + "/user-service/users/" + userId + "/items/" + itemSeq + "/liked";

            ResponseEntity<Integer> likedResponse = restTemplate.exchange(url, HttpMethod.GET, entity, Integer.class);
            isLiked = likedResponse.getBody();

        } else {
            entity = new HttpEntity(null);
        }
        String url = env.getProperty("gateway.ip") + "/item-service/item/" + itemSeq + "/countUp/" + userAge;
        log.info("url : " + url);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        ItemDto itemDto = itemService.getItemInfoDetails(itemSeq);
        itemDto.setIsUserLiked(isLiked);

        List<ItemDto> itemDtoList = itemService.findItemRandomItem();

        List<ItemReviewDto> itemReviewDtoList = itemService.findItemReviewByItemSeq(itemSeq);

        model.addAttribute("itemDto", itemDto);
        model.addAttribute("itemReviewDtoList", itemReviewDtoList);
        model.addAttribute("itemDtoList", itemDtoList);

        return "/page-item-details";
    }

    @RequestMapping("/users/items/liked")
    public String findAllLikedItem(HttpSession session, Model model) throws Exception {
        String token = String.valueOf(session.getAttribute("SS_USER_TOKEN"));

        if (token.length() < 10) {
            model.addAttribute("msg", "로그인된 사용자만 조회할 수 있습니다.");
            model.addAttribute("url", "/user/page-login");

            return "/redirect";
        }

        String url = env.getProperty("gateway.ip") + "/user-service/users/items/liked";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", String.valueOf(session.getAttribute("SS_USER_TOKEN")));


        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<ItemDto> response = restTemplate.exchange(url, GET, entity, ItemDto.class);

        List<ItemDto> itemDtoList = response.getBody().getResponse();


        model.addAttribute("itemDtoList", itemDtoList);


        return "/page-items";
    }


}
