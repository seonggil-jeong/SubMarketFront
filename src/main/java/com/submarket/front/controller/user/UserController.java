package com.submarket.front.controller.user;

import com.submarket.front.dto.ItemDto;
import com.submarket.front.dto.SubDto;
import com.submarket.front.dto.UserDto;
import com.submarket.front.service.impl.UserService;
import com.submarket.front.util.CmmUtil;
import com.submarket.front.vo.ItemLikedRequest;
import com.submarket.front.vo.RequestChangePassword;
import com.submarket.front.vo.RequestLogin;
import com.submarket.front.vo.SubDeleteRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpHead;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpMethod.POST;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final Environment env;
    private final RestTemplate restTemplate;
    private final UserService userService;

    @PostMapping("/user/modifyUserInfo")
    public String modifyUSerInfo(HttpSession session, UserDto userDto, ModelMap model) throws Exception {
        String token = session.getAttribute("SS_USER_TOKEN").toString();
        log.info("userEmail : " + userDto.getUserEmail());

        String resultString = userService.modifyUserInfo(userDto, token);
        log.info("result : " + resultString);

        UserDto rUserDto = userService.getUserInfo((String) session.getAttribute("SS_USER_TOKEN"));
        session.setAttribute("SS_USER_INFO", rUserDto);

        model.addAttribute("msg", resultString);
        model.addAttribute("url", "/user/profile");
        return "/redirect";
    }

    @PostMapping("/user/changePassword")
    public String changePassword(HttpServletRequest request, ModelMap model, HttpSession session) {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newPassword2 = request.getParameter("newPassword2");

        try {
            if (newPassword.equals(newPassword2)) {
                String url = env.getProperty("gateway.ip") + "/user-service/users/changePassword";
                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", CmmUtil.nvl((String) session.getAttribute("SS_USER_TOKEN")));

                RequestChangePassword body = new RequestChangePassword();
                body.setOldPassword(oldPassword);
                body.setNewPassword(newPassword);

                HttpEntity<RequestChangePassword> entity = new HttpEntity<>(body, headers);

                ResponseEntity<String> response = restTemplate.exchange(url, POST, entity, String.class);
                model.addAttribute("msg", response.getBody());
                model.addAttribute("url", "/user/profile");

            } else {
                model.addAttribute("msg", "비밀번호가 일치하지 않습니다");
                model.addAttribute("url", "/user/profile");
            }

        } catch (HttpStatusCodeException statusCodeException) {
            String errorStr = statusCodeException.getResponseBodyAsString(); // get body info
            model.addAttribute("msg", errorStr);
            model.addAttribute("url", "/user/profile");


        }
        return "/redirect";
    }

    @GetMapping("/user/sub/{sellerId}/{itemSeq}")
    public String newSub(@PathVariable int itemSeq, @PathVariable String sellerId, HttpSession session, ModelMap model) throws Exception {
        SubDto subDto = new SubDto();
        subDto.setItemSeq(itemSeq);
        String token = String.valueOf(session.getAttribute("SS_USER_TOKEN"));

        if (token.length() < 10) {
            model.addAttribute("msg", "로그인이 필요합니다");
            model.addAttribute("url", "/user/page-login");

            return "/redirect";
        }
        UserDto userDto = (UserDto) session.getAttribute("SS_USER_INFO");

        String userAddress = userDto.getUserAddress();
        String userAddress2 = CmmUtil.nvl(userDto.getUserAddress2());


        subDto.setUserAddress(userAddress);
        subDto.setUserAddress2(userAddress2);
        subDto.setSellerId(sellerId);

        String rStr = userService.saveSub(subDto, token);

        model.addAttribute("msg", rStr);
        model.addAttribute("url", "/index");

        return "/redirect";
    }

    @GetMapping("/user/sub/delete")
    public String deleteSub(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

        String url = env.getProperty("gateway.ip") + "/user-service/subs/delete";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.valueOf(session.getAttribute("SS_USER_TOKEN")));

        HttpEntity<SubDeleteRequest> entity = new HttpEntity<>(SubDeleteRequest.builder()
                .subSeq(Integer.valueOf(request.getParameter("subSeq"))).build(), headers);

        ResponseEntity<String> response = restTemplate.exchange(url, POST, entity, String.class);

        model.addAttribute("msg", response.getBody());
        model.addAttribute("url", "/user/sublist");


        return "/redirect";

    }

    @PostMapping("/user/join")
    public String insertUser(UserDto userDto, ModelMap model, HttpServletRequest request) throws Exception {
        log.info(this.getClass().getName() + ".insertUser Start!");
        String url = env.getProperty("gateway.ip") + "/user-service/users";
        String userPasswordC1 = userDto.getUserPassword();
        String userPasswordC2 = String.valueOf(request.getParameter("userPassword2"));

        if (!userPasswordC1.equals(userPasswordC2)) {
            model.addAttribute("msg", "비밀번호가 일치하지 않습니다");
            model.addAttribute("url", "/regForm");

            return "/redirect";
        }

        try {
            HttpEntity<UserDto> entity = new HttpEntity<>(userDto);
            ResponseEntity<String> response = restTemplate.exchange(url, POST, entity, String.class);

            model.addAttribute("msg", response.getBody());
            model.addAttribute("url", "/index");

        } catch (HttpStatusCodeException statusCodeException) {
            int code = statusCodeException.getRawStatusCode();

            if (code == 409) {
                model.addAttribute("msg", statusCodeException.getResponseBodyAsString());
                model.addAttribute("url", "/regForm");

            } else {
                model.addAttribute("msg", "Server Error");
                model.addAttribute("url", "/regForm");
            }
        } finally {
            return "/redirect";
        }

    }

    @PostMapping(value = "/user/delete")
    public String deleteUser(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
        // 사용자 Id (TOKEN) and Password (request)로 받아 전송
        String url = env.getProperty("gateway.ip") + "/user-service/users/delete";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.valueOf(session.getAttribute("SS_USER_TOKEN")));
        String rStr = "";

        Map<String, Object> body = new HashMap<>();
        body.put("userPassword", String.valueOf(request.getParameter("userPassword")));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, POST, entity, String.class);

            model.addAttribute("msg", response.getBody());
            model.addAttribute("url", "/index");

            session.invalidate();

        } catch (Exception exception) {
            log.info("Exception : " + exception);
            model.addAttribute("msg", "비밀번호를 확인해 주세요");
            model.addAttribute("url", "/index");
        }

        return "/redirect";
    }


    // 사용자 로그인
    @RequestMapping(value = "/user/login", method = {RequestMethod.POST})
    public String userLogin(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

        log.info(this.getClass().getName() + ".login Start!");
        String url = env.getProperty("gateway.ip") + "/user-service/login";

        try {
            String userId = CmmUtil.nvl(request.getParameter("userId"));
            String userPassword = CmmUtil.nvl(request.getParameter("userPassword"));


            HttpHeaders headers = new HttpHeaders();
            RequestLogin body = new RequestLogin();
            body.setUserId(userId);
            body.setUserPassword(userPassword);

            HttpEntity<RequestLogin> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, POST, entity, String.class);


            UserDto userDto = userService.getUserInfo(response.getHeaders().get("token").get(0));

            log.info("userName : " + userDto.getUserName());

            session.setAttribute("SS_USER_TOKEN", response.getHeaders().get("token").get(0));
            session.setAttribute("SS_USER_INFO", userDto);

            model.addAttribute("msg", "환영합니다");
            model.addAttribute("url", "/index");

        } catch (HttpStatusCodeException httpStatusCodeException) { // 로그인 오류
            int code = httpStatusCodeException.getRawStatusCode();
            log.info("statusCode : " + code);

            if (code == 401) {
                model.addAttribute("msg", "비밀번호 또는 아이디를 확인해 주세요");
                model.addAttribute("url", "/user/page-login");

            }

            if (code == 503) {
                model.addAttribute("msg", "ServerError");
                model.addAttribute("url", "/index");
            }

        } catch (Exception e) { // 500 Error 은 그냥 pass HttpStatusCodeException 에 잡히지 않음
            model.addAttribute("msg", "ServerError");
            model.addAttribute("url", "/index");
        } finally {


            return "/redirect";
        }


    }

    @PostMapping("/user/findPassword")
    public String findPassword(UserDto userDto, ModelMap model) throws Exception {
        try {
            String url = env.getProperty("gateway.ip") + "/user-service/users/fix/find-password";

            HttpEntity<UserDto> entity = new HttpEntity<>(userDto);

            ResponseEntity<String> response = restTemplate.exchange(url, POST, entity, String.class);

            model.addAttribute("msg", response.getBody());
            model.addAttribute("url", "/index");

            return "/redirect";

        } catch (HttpStatusCodeException statusCodeException) {
            String msg = statusCodeException.getResponseBodyAsString();
            log.info("msg : " + msg);
            model.addAttribute("msg", msg);
            model.addAttribute("url", "/index");
            return "/redirect";
        }
    }

    @RequestMapping("/user/liked/{itemSeq}")
    public String itemLiked(@PathVariable int itemSeq, Model model, HttpSession session) throws Exception {

        String token = String.valueOf(session.getAttribute("SS_USER_TOKEN"));

        if (token.length() < 10) {
            model.addAttribute("msg", "로그인이 필요합니다");
            model.addAttribute("url", "/user/page-login");

            return "/redirect";
        }

        String url = env.getProperty("gateway.ip") + "/user-service/users/items/liked";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", String.valueOf(session.getAttribute("SS_USER_TOKEN")));

        ItemLikedRequest request = ItemLikedRequest.builder().itemSeq(itemSeq).build();

        HttpEntity<ItemLikedRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, POST, entity, String.class);

        log.info("msg : " + response.getBody());

        model.addAttribute("msg", response.getBody());
        model.addAttribute("url", "/items/" + itemSeq);


        return "/redirect";


    }
}
