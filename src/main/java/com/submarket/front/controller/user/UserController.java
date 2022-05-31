package com.submarket.front.controller.user;

import com.submarket.front.dto.UserDto;
import com.submarket.front.service.impl.UserService;
import com.submarket.front.util.CmmUtil;
import com.submarket.front.vo.RequestChangePassword;
import com.submarket.front.vo.RequestLogin;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        userDto.setToken(token);
        log.info("userEmail : " + userDto.getUserEmail());

        String resultString = userService.modifyUserInfo(userDto);
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

                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
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
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);


            UserDto userDto = userService.getUserInfo(response.getHeaders().get("token").get(0));

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

            } else {
                model.addAttribute("msg", "Server Error");
                model.addAttribute("url", "/index");

            }

        }
        catch (Exception e){ // 500 Error 은 그냥 pass HttpStatusCodeException 에 잡히지 않음
            model.addAttribute("msg", "ServerError");
            model.addAttribute("url", "/index");
        }
        finally {


            return "/redirect";
        }


    }

}
