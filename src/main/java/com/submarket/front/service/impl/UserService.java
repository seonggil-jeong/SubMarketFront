package com.submarket.front.service.impl;

import com.submarket.front.service.IUserService;
import com.submarket.front.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final RestTemplate restTemplate;
    private final Environment env;

    @Override
    public ResponseUser getUserInfo(String token) throws Exception {
        log.info(this.getClass().getName() + ".getUserInfo");
        String url = env.getProperty("gateway.ip") + "/user-service/user";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);

        Map<String, Object> body = new HashMap<>();

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<ResponseUser> response = restTemplate.exchange(url, HttpMethod.GET, entity, ResponseUser.class);
        return response.getBody();
    }
}
