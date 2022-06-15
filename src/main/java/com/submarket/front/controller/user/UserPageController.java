package com.submarket.front.controller.user;

import com.submarket.front.dto.ItemReviewDto;
import com.submarket.front.dto.OrderDto;
import com.submarket.front.dto.SubDto;
import com.submarket.front.dto.UserDto;
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
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserPageController {
    private final UserService userService;
    private final ItemService itemService;
    private final Environment env;
    private final RestTemplate restTemplate;

    /**
     * page 이동 Controller
     * @return pageInfo
     */

    @RequestMapping("/user/profile")
    public String userProfile(HttpSession session) throws Exception {
            return "/user/page-profile";
    }

    @RequestMapping("/user/sublist")
    public String subList(HttpSession session, ModelMap model) throws Exception {
        String token = String.valueOf(session.getAttribute("SS_USER_TOKEN"));
        List<SubDto> subDtoList = userService.getSubList(token);
        model.addAttribute("subDtoList", subDtoList);
        return "/user/page-sublist";
    }

    @RequestMapping("/user/reviewlist")
    public String reviewList(HttpSession session, ModelMap model) throws Exception {
        String token = (String) session.getAttribute("SS_USER_TOKEN");
        List<ItemReviewDto> itemReviewDtoList = itemService.findItemReviewByUserToken(token);

        model.addAttribute("itemReviewDtoList", itemReviewDtoList);

        return "/user/page-reviewlist";
    }

    @RequestMapping("/user/page-login")
    public String userPageLogin(HttpSession session) {
        return "/user/page-login";
    }
}
