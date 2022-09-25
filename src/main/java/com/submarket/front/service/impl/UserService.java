package com.submarket.front.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.submarket.front.dto.ItemDto;
import com.submarket.front.dto.SubDto;
import com.submarket.front.dto.UserDto;
import com.submarket.front.service.IUserService;
import com.submarket.front.util.CmmUtil;
import com.submarket.front.vo.SubRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final RestTemplate restTemplate;
    private final Environment env;
    private final ItemService itemService;

    @Override
    public UserDto getUserInfo(String token) throws Exception {
        log.info(this.getClass().getName() + ".getUserInfo");
        try {
            String url = env.getProperty("gateway.ip") + "/user-service/users";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);

            Map<String, Object> body = new HashMap<>();

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<UserDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, UserDto.class);
            return response.getBody();

        } catch (Exception exception) {
            return new UserDto();
        }
    }

    @Override
    public String modifyUserInfo(UserDto userDto, String token) throws Exception {

        RestTemplate test = new RestTemplate();
        test.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        log.info(this.getClass().getName() + ".modifyUserInfo");
        String url = env.getProperty("gateway.ip") + "/user-service/users/modify";
        log.info("url : " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);

        HttpEntity<UserDto> entity = new HttpEntity(userDto, headers);

        ResponseEntity<String> response = test.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }

    @Override
    public List<SubDto> getSubList(String token) throws Exception {
        // token 정보를 가지고 userService 에서 구독 정보 불러오기
        List<SubDto> subDtoList = new LinkedList<>();
        String url = env.getProperty("gateway.ip") + "/user-service/subs";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);

            HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);


            ResponseEntity<SubDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, SubDto.class);
            subDtoList =  response.getBody().getResponse();

            if (subDtoList.size() > 0) {
                for (int i = 0; i < subDtoList.size(); i++) {
                    int itemSeq = subDtoList.get(i).getItemSeq();

                    Optional<ItemDto> itemDto = Optional.ofNullable(itemService.getItemInfoDetails(itemSeq));
                    if (itemDto.isPresent()) {
                        subDtoList.get(i).setMainImagePath(itemDto.get().getMainImagePath());
                        subDtoList.get(i).setItemTitle(itemDto.get().getItemTitle());
                        subDtoList.get(i).setItemPrice(itemDto.get().getItemPrice());

                    }
                }
            }


        } catch (HttpStatusCodeException httpStatusCodeException) {
            log.info("code : " + httpStatusCodeException);

        } catch (Exception e) {
            log.info("Server Error : " + e);

        } finally {

            return subDtoList;

        }
    }

    @Override
    public String saveSub(SubDto subDto, String token) throws Exception {
        log.info(this.getClass().getName() + "saveSub Start!");
        String url = env.getProperty("gateway.ip") + "/user-service/subs";

        String rStr = "";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);

        SubRequest request = SubRequest.builder()
                .itemSeq(subDto.getItemSeq())
                .userId(subDto.getUserId()).build();
        HttpEntity<SubRequest> entity = new HttpEntity<>(request, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            rStr = response.getBody();

        } catch (HttpStatusCodeException statusCodeException) {
            log.info("HttpStatusCodeException : " + statusCodeException);
            int code = statusCodeException.getRawStatusCode();

            if (code == 400) {
                rStr = statusCodeException.getResponseBodyAsString();
            } else {
                rStr = "Server Error";
            }
        } finally {
        log.info(this.getClass().getName() + "saveSub End!");
            return rStr;
        }

    }
}
